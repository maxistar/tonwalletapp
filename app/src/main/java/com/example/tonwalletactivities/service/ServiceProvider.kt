package com.example.tonwalletactivities.service

object ServiceProvider {

    private lateinit var walletService:WalletService;
    fun getWalletService(): WalletService {
        //if (walletService.) {
            walletService = WalletService();
        //}
        return walletService;
    }
}