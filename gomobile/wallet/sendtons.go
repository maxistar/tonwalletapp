package wallet

import (
	"context"
	"fmt"
	"log"
	"strings"

	"github.com/xssnick/tonutils-go/address"
	"github.com/xssnick/tonutils-go/liteclient"
	"github.com/xssnick/tonutils-go/tlb"
	"github.com/xssnick/tonutils-go/ton"
	"github.com/xssnick/tonutils-go/ton/wallet"
)

func SendTons(words string, version int, configUrl string, destination string, amount float64, comment string) string {

	seed := strings.Split(words, " ")

	client := liteclient.NewConnectionPool()
	ctx := client.StickyContext(context.Background())

	err := client.AddConnectionsFromConfigUrl(context.Background(), configUrl)
	if err != nil {
		log.Println(err)
		return "error"
	}
	api := ton.NewAPIClient(client)

	w, err := wallet.FromSeed(api, seed, wallet.Version(version))
	if err != nil {
		log.Println(err)
		return "error"
	}

	addr := address.MustParseAddr(destination)

	err = w.Transfer(ctx, addr, tlb.MustFromTON(fmt.Sprintf("%f", amount)),
		comment, true)

	if err != nil {
		log.Fatalln("Transfer err:", err.Error())
		return "error"
	}

	return "ok"
}
