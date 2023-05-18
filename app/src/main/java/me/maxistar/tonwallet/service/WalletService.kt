package me.maxistar.tonwallet.service

import hello.Hello
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WalletService {
    suspend fun getNewWalletWords(): String {
        return withContext(Dispatchers.IO) {
            Hello.getNewWalletString()
        }
    }
}