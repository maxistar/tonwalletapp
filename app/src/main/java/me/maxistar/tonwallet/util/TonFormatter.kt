package me.maxistar.tonwallet.util

object TonFormatter {
    fun nanoTonsToString(value:Long): String {
        return ((1.0 * value) / 1000000000.0).toString();
    }

    /**
     * todo cover this by test
     */
    fun nanoTonsToHtmlString(value:Long): String {
        val longNull: Long = 0
        if (value % 1000000000 != longNull) {
            val tons = (1.0 * value) / 1000000000.0
            val integralPart = (tons).toInt()
            val floatPart = tons - integralPart;
            val floatingPartString = floatPart.toString()
            val floatingPart = floatingPartString.substring(1);
            return "$integralPart<small>$floatingPart</small>";
        }
        val tons = (1.0 * value) / 1000000000.0
        val integralPart = (tons).toInt()
        return "$integralPart";
    }

    fun addressShorten(address: String): String {
        val firstPart = address.substring(0, 4)
        val lastPart = address.substring(address.length - 4)
        return "$firstPart...$lastPart"
    }
}