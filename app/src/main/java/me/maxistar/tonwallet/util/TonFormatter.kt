package me.maxistar.tonwallet.util

import me.maxistar.tonwallet.model.TransactionDisplayItem
import me.maxistar.tonwallet.model.TransactionItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TonFormatter {
    fun nanoTonsToString(value:Long): String {
        return ((1.0 * value) / 1000000000.0).toString()
    }

    /**
     * todo cover this by test
     */
    fun nanoTonsToHtmlString(value:Long): String {
        val longNull: Long = 0
        if (value % 1000000000 != longNull) {
            val tons = (1.0 * value) / 1000000000.0
            val integralPart = (tons).toInt()
            val floatPart = tons - integralPart
            val floatingPartString = floatPart.toString()
            val floatingPart = floatingPartString.substring(1)
            return "$integralPart<small>$floatingPart</small>"
        }
        val tons = (1.0 * value) / 1000000000.0
        val integralPart = (tons).toInt()
        return "$integralPart"
    }

    fun addressShorten(address: String): String {
        val firstPart = address.substring(0, 4)
        val lastPart = address.substring(address.length - 4)
        return "$firstPart...$lastPart"
    }

    fun formatTransactions(transactions: List<TransactionItem>): Collection<TransactionDisplayItem> {
        val transactions_: MutableList<TransactionDisplayItem> = mutableListOf()

        val sortedListOperson = transactions
            .sortedWith(object : Comparator <TransactionItem> {
                override fun compare (p0: TransactionItem, p1: TransactionItem) : Int {
                    if (p0.transaction_time > p1.transaction_time) {
                        return -1
                    }
                    return 1
                }
            })

        var previousDateFormatted = ""

        for (item in sortedListOperson) {

            val date1 = Date(item.transaction_time / 1000)
            val timePattern = "HH:mm"
            val simpleDateFormat = SimpleDateFormat(timePattern, Locale.getDefault())
            val timeFormatted: String = simpleDateFormat.format(date1)

            val datePattern = "MMMM dd"
            val simpleDateFormat2 = SimpleDateFormat(datePattern, Locale.getDefault())
            val dateFormatted: String = simpleDateFormat2.format(date1)
            var dateToDisplay = ""

            if (dateFormatted != previousDateFormatted) {
                previousDateFormatted = dateFormatted
                dateToDisplay = dateFormatted
            }


            transactions_.add(TransactionDisplayItem(item.amount, item.comment, item.address, item.transactionType, dateToDisplay, timeFormatted))
        }

        return transactions_
    }
}