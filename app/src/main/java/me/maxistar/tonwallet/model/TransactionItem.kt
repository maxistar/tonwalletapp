package me.maxistar.tonwallet.model

class TransactionItem(amount_: Long, comment_: String, address_: String, transaction_type_: String, transaction_time_: Long) {
    val amount: Long = amount_
    val comment: String = comment_
    val address: String = address_
    val transactionType: String = transaction_type_
    val transaction_time: Long = transaction_time_
}