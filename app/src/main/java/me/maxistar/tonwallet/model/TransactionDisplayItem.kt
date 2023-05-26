package me.maxistar.tonwallet.model

class TransactionDisplayItem(amount_: Long, comment_: String, address_: String, transaction_type_: String, date_: String, time_: String)  {
    val amount: Long = amount_
    val comment: String = comment_
    val address: String = address_
    val transactionType: String = transaction_type_
    val date: String = date_
    val time: String = time_
}