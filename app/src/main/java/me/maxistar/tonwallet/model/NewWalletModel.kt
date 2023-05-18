package me.maxistar.tonwallet.model

class NewWalletModel {
    private lateinit var secretPhrase:WalletSecretPhrase

    constructor(secretPhrase: WalletSecretPhrase) {
        this.secretPhrase = secretPhrase
    }
}