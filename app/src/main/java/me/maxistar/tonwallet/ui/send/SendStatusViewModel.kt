package me.maxistar.tonwallet.ui.send

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.maxistar.tonwallet.service.ServiceProvider

class SendStatusViewModel : ViewModel() {
    private var operationStatus: String = "new"

    private val _text = MutableLiveData<String>().apply {
        value = operationStatus
    }

    fun sendTransaction(seed: String, walletVersion: Long, configUrl: String, recipient: String, amount: Double, comment: String) {
        viewModelScope.launch {
            operationStatus = ServiceProvider.getWalletService().sendTransaction(
                seed,
                walletVersion,
                configUrl,
                recipient,
                amount,
                comment
            )
            _text.value = operationStatus
        }
    }

    val liveOperationStatus: LiveData<String> = _text
}