package me.maxistar.tonwallet.service

object ServiceProvider {

    private var walletService:WalletService? = null;

    private var settingsService:SettingsService? = null;
    fun getWalletService(): WalletService {
        if (walletService === null) {
            walletService = WalletService();
        }
        return walletService as WalletService;
    }

    fun getSettingsService(): SettingsService {
        if (settingsService === null) {
            settingsService = SettingsService()
        }
        return settingsService as SettingsService
    }
}