package com.example.tonwalletactivities.service

import com.example.tonwalletactivities.model.WalletSecretPhrase
import hello.Hello

class WalletService {
    fun getNewWalletWords(): WalletSecretPhrase {
        return WalletSecretPhrase(Hello.getNewWalletString())
    }
}