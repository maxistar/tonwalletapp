package wallet

import (
	"context"
	"fmt"
	"log"
	"strings"

	"github.com/xssnick/tonutils-go/liteclient"
	"github.com/xssnick/tonutils-go/ton"
	"github.com/xssnick/tonutils-go/ton/wallet"
)

func GetBalance(words string) string {
	client := liteclient.NewConnectionPool()

	configUrl := "https://ton-blockchain.github.io/testnet-global.config.json"
	err := client.AddConnectionsFromConfigUrl(context.Background(), configUrl)
	if err != nil {
		log.Println(err)
	}
	api := ton.NewAPIClient(client)

	seed := strings.Split(words, " ")

	fmt.Println(words)

	w, err := wallet.FromSeed(api, seed, wallet.V3)
	if err != nil {
		log.Println(err)
	}

	block, err := api.CurrentMasterchainInfo(context.Background())
	if err != nil {
		log.Println(err)
	}

	balance, err := w.GetBalance(context.Background(), block)
	if err != nil {
		log.Println(err)
	}

	address := w.Address()

	fmt.Println(balance)
	fmt.Println(words)
	fmt.Println(address)

	return balance.String()
}
