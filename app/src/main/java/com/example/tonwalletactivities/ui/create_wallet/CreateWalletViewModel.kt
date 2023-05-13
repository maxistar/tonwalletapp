package com.example.tonwalletactivities.ui.create_wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tonwalletactivities.model.WalletSecretPhrase
import com.example.tonwalletactivities.service.ServiceProvider

class CreateWalletViewModel : ViewModel() {

    private val newWalletWords: WalletSecretPhrase = ServiceProvider.getWalletService().getNewWalletWords()

    private val _text = MutableLiveData<WalletSecretPhrase>().apply {
        value = newWalletWords
    }

    val text: LiveData<WalletSecretPhrase> = _text

}