package com.example.tonwalletactivities.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hello.Hello;


class DashboardViewModel : ViewModel() {

    var greetings: String = Hello.greetings("Android and Gopher")

    private val _text = MutableLiveData<String>().apply {
        value = greetings
    }
    val text: LiveData<String> = _text
}