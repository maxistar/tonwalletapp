package com.example.tonwalletactivities.ui.create_wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tonwalletactivities.service.ServiceProvider

class CreateWalletViewModel : ViewModel() {

    private val newWalletWords: String = ServiceProvider.getWalletService().getNewWalletWords()

    private val _text = MutableLiveData<String>().apply {
        value = newWalletWords
    }
    val text: LiveData<String> = _text

}