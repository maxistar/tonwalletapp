package me.maxistar.tonwallet.model

class AccessCodeModel {
    var code1: Char? = null
    var code2: Char? = null
    var code3: Char? = null
    var code4: Char? = null
    var code5: Char? = null
    var code6: Char? = null

    fun setCode(code: String) {
        code1 = code[0]
        code2 = code[1]
        code3 = code[2]
        code4 = code[3]
        if (code.length > 4) {
            code5 = code[4]
            code6 = code[5]
        }
    }

    override fun toString(): String {
        if (code5 != null) {
            return "$code1$code2$code3$code4$code5$code6"
        }
        return "$code1$code2$code3$code4"
    }
}