package main

import (
	"binding/wallet"
	"fmt"

	wallet2 "github.com/xssnick/tonutils-go/ton/wallet"
)

func main() {

	fmt.Println("general test:")

	fmt.Println(wallet.Greetings("test"))

	fmt.Println("generate new wallet:")

	fmt.Println(wallet.GetNewWalletString())

	fmt.Println("generate new wallet json:")

	fmt.Println(wallet.GetNewWalletInfo(int(wallet2.V3), "https://ton-blockchain.github.io/testnet-global.config.json"))

	fmt.Println("generate new wallet with the address:")

	fmt.Println(wallet.GenerateNewWallet(wallet2.V3, "https://ton-blockchain.github.io/testnet-global.config.json"))

}
