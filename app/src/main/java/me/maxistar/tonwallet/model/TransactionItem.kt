package me.maxistar.tonwallet.model

class TransactionItem(amount_: Long, comment_: String, address_: String, transactiont_type_: String) {
    var amount: Long = amount_
    var comment: String = comment_
    var address: String = address_
    var transactionType: String = transactiont_type_
}