package me.maxistar.tonwallet.ui.wallet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.maxistar.tonwallet.model.TransactionDisplayItem
import me.maxistar.tonwallet.model.TransactionItem
import me.maxistar.tonwallet.service.ServiceProvider
import me.maxistar.tonwallet.util.TonFormatter

class WalletViewModel : ViewModel() {

    private var _balance: Long = 0

    private var _transactions: MutableList<TransactionDisplayItem> = mutableListOf()

    private val _liveTransactions = MutableLiveData<MutableList<TransactionDisplayItem>>().apply {
        value = _transactions
    }

    private val _liveBalance = MutableLiveData<Long>().apply {
        value = _balance
    }

    fun updateWallet(seed: String, walletVersion: Long, configUrl: String): Job {
        Log.w("updateWallet", "UpdateWallet")
        val walletService = ServiceProvider.getWalletService();

        return viewModelScope.launch {
            val transactions = walletService.getTransactionsSuspended(seed, walletVersion, configUrl)
            if (transactions != null) {
                _transactions.clear()
                _transactions.addAll(TonFormatter.formatTransactions(transactions.transactions))
                _liveTransactions.value = _transactions
            }

            _liveBalance.value = walletService.getBalanceSuspended(seed, walletVersion, configUrl)
        }
    }

    val transactions: LiveData<MutableList<TransactionDisplayItem>> = _liveTransactions

    val balance: LiveData<Long> = _liveBalance

}