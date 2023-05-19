package wallet

import (
	"github.com/xssnick/tonutils-go/ton/wallet"
	"strings"
)

func GetNewWalletString() string {
	words := wallet.NewSeed()
	return strings.Join(words, " ")
}
