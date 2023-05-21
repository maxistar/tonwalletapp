package me.maxistar.tonwallet.service

import android.util.Log
import wallet.Wallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.maxistar.tonwallet.model.TransactionItem
import me.maxistar.tonwallet.model.WalletDetails
import me.maxistar.tonwallet.model.WalletHistoryDetails
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

    fun getTransactions(seed: String, walletVersion: Long, configUrl: String?): WalletHistoryDetails? {
        val result = Wallet.getTransactions(seed, walletVersion, configUrl);
        if (result == "") {
            return null
        }
        val jsonValue = JSONObject(result)
        val balance = jsonValue.getLong("Balance")
        val status = jsonValue.getString("Status")
        val data = jsonValue.getString("Data")
        val transactionsList = jsonValue.getJSONArray("Transactions")
        val transactions = mutableListOf<TransactionItem>()
        for (i in 0 until transactionsList.length()) {
            val item = transactionsList.getJSONObject(i)
            transactions.add(TransactionItem(item.getLong("Amount"), "comment to do", item.getString("Address")))
        }
        Log.w("transactions", result.toString())
        return WalletHistoryDetails(balance, status, data, transactions)
    }
}