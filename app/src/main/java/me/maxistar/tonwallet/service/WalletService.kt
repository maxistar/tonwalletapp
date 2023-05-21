package me.maxistar.tonwallet.service

import android.util.Log
import wallet.Wallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.maxistar.tonwallet.model.TransactionItem
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

    fun getBalance(seed: String, walletVersion: Long, configUrl: String?): Long {
        val result = Wallet.getBalance(seed, walletVersion, configUrl);
        Log.w("balance", result.toString())
        val jsonValue = JSONObject(result)
        val details = jsonValue.getLong("NanoTons")
        return details;
    }

    fun getTransactions(seed: String, walletVersion: Long, configUrl: String?): List<TransactionItem> {
        val result = Wallet.getTransactions(seed, walletVersion, configUrl);
        Log.w("transactions", result.toString())
        val transactions = mutableListOf<TransactionItem>()
        return transactions
    }
}