package me.maxistar.tonwallet.model

class WalletSecretPhrase(words: String) {
    private var words: List<String>

    fun getWords(): List<String> {
        return words
    }

    init {
        val wordsSplitted = words.split("\\s".toRegex())
        this.words = wordsSplitted
    }

}