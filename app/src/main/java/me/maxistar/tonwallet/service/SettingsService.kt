package me.maxistar.tonwallet.service

import android.content.Context
import androidx.preference.PreferenceManager

class SettingsService {

    fun storeWallet(address: String, secret: String) {

    }

    fun getTonConfiguration(context: Context): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString("configurl", "https://ton-blockchain.github.io/testnet-global.config.json") as String;
    }

    fun getWalletAddress(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        return "";
    }

    fun getWalletSecretPhrase(): String {
        return "";
    }

}