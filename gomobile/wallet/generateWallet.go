package wallet

import (
	"context"
	"log"
	"strings"

	"github.com/xssnick/tonutils-go/liteclient"
	"github.com/xssnick/tonutils-go/ton"
	"github.com/xssnick/tonutils-go/ton/wallet"
)

type WalletInfo struct {
	Seed    string
	Address string
}

/**
 * todo test this witout internet
 */
func GenerateNewWallet(version wallet.Version, configUrl string) WalletInfo {
	seed := wallet.NewSeed()
	client := liteclient.NewConnectionPool()

	// configUrl := "https://ton-blockchain.github.io/testnet-global.config.json"
	err := client.AddConnectionsFromConfigUrl(context.Background(), configUrl)
	if err != nil {
		log.Println(err)
	}
	api := ton.NewAPIClient(client)

	wallet, err := wallet.FromSeed(api, seed, version)
	if err != nil {
		log.Println(err)
	}

	address := wallet.Address()

	result := WalletInfo{
		Seed:    strings.Join(seed, " "),
		Address: address.String(),
	}
	return result
}
