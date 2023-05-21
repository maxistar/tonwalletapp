package wallet

import (
	"context"
	"encoding/json"
	"fmt"
	"log"
	"strings"

	"github.com/xssnick/tonutils-go/liteclient"
	"github.com/xssnick/tonutils-go/ton"
	"github.com/xssnick/tonutils-go/ton/wallet"
)

func GetNewWalletInfo(version int, configUrl string) string {
	seed := wallet.NewSeed()

	client := liteclient.NewConnectionPool()

	err := client.AddConnectionsFromConfigUrl(context.Background(), configUrl)
	if err != nil {
		log.Println(err)
		return ""
	}
	api := ton.NewAPIClient(client)

	wallet, err := wallet.FromSeed(api, seed, wallet.Version(version))
	if err != nil {
		log.Println(err)
		return ""
	}

	address := wallet.Address()

	info := WalletInfo{
		Seed:    strings.Join(seed, " "),
		Address: address.String(),
	}

	b, err := json.Marshal(info)
	if err != nil {
		fmt.Println(err)
		return ""
	}

	return string(b)
}
