package me.maxistar.tonwallet.service

import android.util.Log
import wallet.Wallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.maxistar.tonwallet.model.WalletDetails
import org.json.JSONObject
import wallet.WalletInfo

class WalletService {
    suspend fun getNewWalletWords(): String {
        return withContext(Dispatchers.IO) {
            Wallet.getNewWalletString()
        }
    }

    suspend fun getNewWalletInfo(walletVersion: Long, configUrl: String?): WalletDetails {
        return withContext(Dispatchers.IO) {
            parseNewWalletInfo(walletVersion, configUrl)
        }
    }

    fun parseNewWalletInfo(walletVersion: Long, configUrl: String?): WalletDetails {
        val result = Wallet.getNewWalletInfo(walletVersion, configUrl)
        val jsonValue = JSONObject(result)
        val details = WalletDetails(jsonValue.getString("Seed"), jsonValue.getString("Address"))
        return details;
    }

    fun loadWalletInfo(seed: String, walletVersion: Long, configUrl: String?): WalletDetails {
        val result = Wallet.getAddressInfo(seed, walletVersion, configUrl)
        val jsonValue = JSONObject(result)
        val details = WalletDetails(jsonValue.getString("Seed"), jsonValue.getString("Address"))
        return details;
    }
}