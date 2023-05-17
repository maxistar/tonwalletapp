package com.example.tonwalletactivities.ui.create_wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletactivities.service.ServiceProvider
import kotlinx.coroutines.launch

class CreateWalletViewModel : ViewModel() {

    private var newWalletWords: String = ""

    fun generateNewWallet() {
        viewModelScope.launch {
            _text.value = ServiceProvider.getWalletService().getNewWalletWords()
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = newWalletWords
    }

    val text: LiveData<String> = _text

}