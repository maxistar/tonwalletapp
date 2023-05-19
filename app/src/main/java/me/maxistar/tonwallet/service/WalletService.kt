package me.maxistar.tonwallet.service

import wallet.Wallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WalletService {
    suspend fun getNewWalletWords(): String {
        return withContext(Dispatchers.IO) {
            Wallet.getNewWalletString()
        }
    }
}