package com.example.tonwalletactivities.model

class WalletSecretPhrase {
    private var words: List<String>

    fun getWords(): List<String> {
        return words
    }

    constructor(words: String) {
        val words = words.split("\\s".toRegex());
        this.words = words;
    }

}