package me.maxistar.tonwallet.model

class AccessCodeModel {
    var code1: Char? = null
    var code2: Char? = null
    var code3: Char? = null
    var code4: Char? = null

    override fun toString(): String {
        return "$code1$code2$code3$code4"
    }
}