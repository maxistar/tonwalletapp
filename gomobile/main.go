package main

import (
	"binding/wallet"
	"fmt"
)

func main() {

	fmt.Println("general test:")

	fmt.Println(wallet.Greetings("test"))

	fmt.Println("generate new wallet:")

	fmt.Println(wallet.GetNewWalletString())
}
