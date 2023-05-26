package wallet

import (
	"context"
	"encoding/json"
	"fmt"
	"log"
	"math/big"
	"strings"

	"github.com/xssnick/tonutils-go/liteclient"
	"github.com/xssnick/tonutils-go/ton"
	"github.com/xssnick/tonutils-go/ton/wallet"
)

type BalanceInfo struct {
	NanoTons *big.Int
}

func GetBalance(words string, version int, configUrl string) string {
	client := liteclient.NewConnectionPool()

	err := client.AddConnectionsFromConfigUrl(context.Background(), configUrl)
	if err != nil {
		log.Println(err)
		return ""
	}
	api := ton.NewAPIClient(client)

	seed := strings.Split(words, " ")


	w, err := wallet.FromSeed(api, seed, wallet.Version(version))
	if err != nil {
		log.Println(err)
		return ""
	}

	block, err := api.CurrentMasterchainInfo(context.Background())
	if err != nil {
		log.Println(err)
		return ""
	}

	balance, err := w.GetBalance(context.Background(), block)
	if err != nil {
		log.Println(err)
		return ""
	}

	info := BalanceInfo{
		NanoTons: balance.NanoTON(),
	}

	b, err := json.Marshal(info)
	if err != nil {
		fmt.Println(err)
		return ""
	}

	return string(b)
}
