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

	fmt.Println(words)

	w, err := wallet.FromSeed(api, seed, wallet.Version(version))
	if err != nil {
		log.Println(err)
	}

	//block, err := api.CurrentMasterchainInfo(context.Background())
	//if err != nil {
	//	log.Println(err)
	//}

	//balance, err := w.GetBalance(context.Background(), block)
	//if err != nil {
	//	log.Println(err)
	//}

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

	// ccount, err := api.GetAccount(context.Background(), b, w.Address())
	// if err != nil {
	//	log.Fatalln("get account err:", err.Error())
	//	return ""
	// }

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
		in := new(big.Int)
		// oldest = first in list
		for _, t := range list {
			fmt.Println(t.String())

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
					})
				}
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
