package me.maxistar.tonwallet.service

object ServiceProvider {

    private var walletService:WalletService? = null;
    fun getWalletService(): WalletService {
        if (walletService == null) {
            walletService = WalletService();
        }
        return walletService as WalletService;
    }
}