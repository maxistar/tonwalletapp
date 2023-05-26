package me.maxistar.tonwallet.ui.wallet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.maxistar.tonwallet.model.TransactionItem
import me.maxistar.tonwallet.service.ServiceProvider

class WalletViewModel : ViewModel() {

    private var _balance: Long = 0

    private var _transactions: MutableList<TransactionItem> = mutableListOf()

    private val _liveTransactions = MutableLiveData<MutableList<TransactionItem>>().apply {
        value = _transactions
    }

    private val _liveBalance = MutableLiveData<Long>().apply {
        value = _balance
    }

    fun updateWallet(seed: String, walletVersion: Long, configUrl: String) {
        Log.w("updateWallet", "UpdateWallet")
        val walletService = ServiceProvider.getWalletService();

        viewModelScope.launch {
            val transactions = walletService.getTransactionsSuspended(seed, walletVersion, configUrl)
            if (transactions != null) {
                _transactions.clear()
                _transactions.addAll(transactions.transactions)
                _liveTransactions.value = _transactions
            }

            _liveBalance.value = walletService.getBalanceSuspended(seed, walletVersion, configUrl)
        }
    }

    val transactions: LiveData<MutableList<TransactionItem>> = _liveTransactions

    val balance: LiveData<Long> = _liveBalance

}