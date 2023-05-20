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

func GetAddressInfo(words string, version int, configUrl string) string {
	seed := strings.Split(words, " ")

	client := liteclient.NewConnectionPool()

	err := client.AddConnectionsFromConfigUrl(context.Background(), configUrl)
	if err != nil {
		log.Println(err)
	}
	api := ton.NewAPIClient(client)

	wallet, err := wallet.FromSeed(api, seed, wallet.Version(version))
	if err != nil {
		log.Println(err)
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
