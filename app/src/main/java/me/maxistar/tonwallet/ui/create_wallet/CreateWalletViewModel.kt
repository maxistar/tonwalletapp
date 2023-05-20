package me.maxistar.tonwallet.ui.create_wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import me.maxistar.tonwallet.service.ServiceProvider
import kotlinx.coroutines.launch

class CreateWalletViewModel : ViewModel() {

    var newWalletWords: String = ""

    var newWalletAddress: String = ""

    fun generateNewWallet(walletVersion: Long, configUrl: String) {
        viewModelScope.launch {
            val walletInfo = ServiceProvider.getWalletService().getNewWalletInfo(walletVersion, configUrl)
            _text.value = walletInfo.getSeed();
            newWalletAddress = walletInfo.getPublicAddress();
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = newWalletWords
    }



    val text: LiveData<String> = _text

}