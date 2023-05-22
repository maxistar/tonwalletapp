package me.maxistar.tonwallet.model

class AccessCodeModel {
    var code1: Char? = null
    var code2: Char? = null
    var code3: Char? = null
    var code4: Char? = null

    fun setCode(code: String) {
        code1 = code[0];
        code2 = code[1];
        code3 = code[2];
        code4 = code[3];
    }

    override fun toString(): String {
        return "$code1$code2$code3$code4"
    }
}