package me.maxistar.tonwallet.model

class WalletHistoryDetails(_balance: Long, _status: String, _data: String, _transactions: List<TransactionItem>) {
    val balance: Long = _balance
    val status: String = _status
    val data: String = _data
    val transactions: List <TransactionItem> = _transactions
}