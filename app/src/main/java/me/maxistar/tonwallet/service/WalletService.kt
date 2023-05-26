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
        if (result == "") {
            // todo add proper error handling
            return 0
        }
        val jsonValue = JSONObject(result)
        val details = jsonValue.getLong("NanoTons")
        return details;
    }

    suspend fun getBalanceSuspended(seed: String, walletVersion: Long, configUrl: String?): Long {
        return withContext(Dispatchers.IO) {
            getBalance(seed, walletVersion, configUrl)
        }
    }

    fun getTransactions(seed: String, walletVersion: Long, configUrl: String?): WalletHistoryDetails? {
        val result = Wallet.getTransactions(seed, walletVersion, configUrl);
        Log.w("responce", result)
        if (result == "") {
            return null
        }
        try {
            val jsonValue = JSONObject(result)
            val balance = jsonValue.getLong("Balance")
            val status = jsonValue.getString("Status")
            val data = jsonValue.getString("Data")
            val transactionsList = jsonValue.getJSONArray("Transactions")
            val transactions = mutableListOf<TransactionItem>()
            for (i in 0 until transactionsList.length()) {
                val item = transactionsList.getJSONObject(i)
                transactions.add(
                    TransactionItem(
                        item.getLong("Amount"),
                        "comment to do",
                        item.getString("Address"),
                        item.getString("Type"),
                        item.getLong("LT")
                    )
                )
            }
            Log.w("transactions", result.toString())
            return WalletHistoryDetails(balance, status, data, transactions)
        } catch (e: Exception) {
            return null
        }
    }

    suspend fun getTransactionsSuspended(seed: String, walletVersion: Long, configUrl: String?): WalletHistoryDetails? {
        return withContext(Dispatchers.IO) {
            getTransactions(seed, walletVersion, configUrl)
        }
    }

    suspend fun sendTransaction(seed: String, walletVersion: Long, configUrl: String, recipient: String, amount: Double, comment: String): String {
        return withContext(Dispatchers.IO) {
            Wallet.sendTons(seed, walletVersion, configUrl, recipient, amount, comment)
        }
    }
}