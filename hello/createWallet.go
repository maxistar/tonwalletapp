package hello

import (
	"context"
	"log"
	"strings"

	"github.com/xssnick/tonutils-go/liteclient"
	"github.com/xssnick/tonutils-go/ton/wallet"
)

func GetNewWalletString() string {
	client := liteclient.NewConnectionPool()

	configUrl := "https://ton-blockchain.github.io/testnet-global.config.json"
	err := client.AddConnectionsFromConfigUrl(context.Background(), configUrl)
	if err != nil {
		log.Println(err)
	}

	words := wallet.NewSeed()
	return strings.Join(words, " ")
}
