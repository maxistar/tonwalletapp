package me.maxistar.tonwallet.model

class WalletDetails(seed_: String, publicAddress_: String) {
    private val seed = seed_
    private val publicAddress = publicAddress_

    fun getSeed(): String {
        return seed
    }

    fun getPublicAddress(): String {
        return publicAddress
    }
}