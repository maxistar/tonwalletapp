package wallet

import (
	"strings"

	"github.com/xssnick/tonutils-go/ton/wallet"
)

func GetNewWalletString() string {
	words := wallet.NewSeed()
	return strings.Join(words, " ")
}
