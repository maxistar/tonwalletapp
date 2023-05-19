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

func Greetings(name string) string {
	client := liteclient.NewConnectionPool()

	configUrl := "https://ton-blockchain.github.io/testnet-global.config.json"
	err := client.AddConnectionsFromConfigUrl(context.Background(), configUrl)
	if err != nil {
		log.Println(err)
	}
	api := ton.NewAPIClient(client)

	// words := wallet.NewSeed() // strings.Split("birth pattern ...", " ")
	words := strings.Split("salad able company apology able apology audit salad update provide dance update ginger destroy provide destroy intact salute vivid apology team oyster sword destroy", " ")

	fmt.Println(words)

	w, err := wallet.FromSeed(api, words, wallet.V3)
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

	return fmt.Sprintf("Hello!!!!, %s! %v %v %v", name, words, balance, address)
}
