package me.maxistar.tonwallet.model

class TransactionItem(amount_: Long, comment_: String, address_: String) {
    var amount: Long = amount_
    var comment: String = comment_
    var address: String = address_
}