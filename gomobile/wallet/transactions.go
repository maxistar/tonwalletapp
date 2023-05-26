package wallet

import (
	"context"
	"encoding/json"
	"fmt"
	"log"
	"math/big"
	"strings"

	"github.com/xssnick/tonutils-go/liteclient"
	"github.com/xssnick/tonutils-go/tlb"
	"github.com/xssnick/tonutils-go/ton"
	"github.com/xssnick/tonutils-go/ton/wallet"
)

type TransactionInfo struct {
	LT      uint64
	Address string
	Amount  *big.Int
	Type    string
}

type AccountInfo struct {
	Balance      *big.Int
	Status       string
	Data         string
	Transactions []TransactionInfo
}

func GetTransactions(words string, version int, configUrl string) string {
	client := liteclient.NewConnectionPool()

	err := client.AddConnectionsFromConfigUrl(context.Background(), configUrl)
	if err != nil {
		log.Println(err)
	}
	api := ton.NewAPIClient(client)

	seed := strings.Split(words, " ")

	w, err := wallet.FromSeed(api, seed, wallet.Version(version))
	if err != nil {
		log.Println(err)
	}

	info := AccountInfo{
		//Balance: balance.NanoTON().Uint64(),
		//NanoTons: balance.NanoTON(),
		Transactions: nil,
	}

	// we need fresh block info to run get methods
	ctx := client.StickyContext(context.Background())

	b, err := api.CurrentMasterchainInfo(ctx)
	if err != nil {
		log.Fatalln("get block err:", err.Error())
		return ""
	}

	addr := w.Address()

	res, err := api.WaitForBlock(b.SeqNo).GetAccount(ctx, b, addr)
	if err != nil {
		log.Fatalln("get account err:", err.Error())
		return ""
	}

	if res.IsActive {
		info.Balance = res.State.Balance.NanoTON()
		info.Status = string(res.State.Status)
		fmt.Printf("Status: %s\n", res.State.Status)
		fmt.Printf("Balance: %s TON\n", res.State.Balance.TON())
		if res.Data != nil {
			fmt.Printf("Data: %s\n", res.Data.Dump())
			info.Data = res.Data.Dump()
		}
	}

	// take last tx info from account info
	lastHash := res.LastTxHash
	lastLt := res.LastTxLT

	fmt.Printf("\nTransactions:\n")
	for {
		// last transaction has 0 prev lt
		if lastLt == 0 {
			break
		}

		// load transactions in batches with size 15
		list, err := api.ListTransactions(ctx, addr, 15, lastLt, lastHash)
		if err != nil {
			log.Printf("send err: %s", err.Error())
			return ""
		}

		// oldest = first in list
		for _, t := range list {
			var destinations []string
			in, out := new(big.Int), new(big.Int)
			fmt.Println(t.String())

			if t.IO.Out != nil {
				listOut, err := t.IO.Out.ToSlice()
				if err != nil {
					continue
				}

				for _, m := range listOut {
					destinations = append(destinations, m.Msg.DestAddr().String())
					if m.MsgType == tlb.MsgTypeInternal {
						out.Add(out, m.AsInternal().Amount.NanoTON())
					}
				}
			}

			//incomment transaction
			if t.IO.In != nil {

				if t.IO.In.MsgType == tlb.MsgTypeInternal {
					in = t.IO.In.AsInternal().Amount.NanoTON()
				}
				if in.Cmp(big.NewInt(0)) != 0 {
					intTx := t.IO.In.AsInternal()
					intTx.Amount.NanoTON()

					info.Transactions = append(info.Transactions, TransactionInfo{
						LT:      t.LT,
						Address: intTx.SrcAddr.String(),
						Amount:  in,
						Type:    "in",
					})
				}
			}

			if out.Cmp(big.NewInt(0)) != 0 {
				info.Transactions = append(info.Transactions, TransactionInfo{
					LT:      t.LT,
					Address: strings.Join(destinations, " "),
					Amount:  out,
					Type:    "out",
				})
			}
		}

		// set previous info from the oldest transaction in list
		lastHash = list[0].PrevTxHash
		lastLt = list[0].PrevTxLT
	}

	r, err := json.Marshal(info)
	if err != nil {
		fmt.Println(err)
		return ""
	}

	return string(r)
}
