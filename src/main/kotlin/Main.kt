fun main() {

    var result = calcFee(10_000, previousTransfersSumMonth = 35000)
    println(result)
}

fun calcFee(transferAmount: Int, cardType: String = "VK Pay", previousTransfersSumMonth: Int = 0): Number {

    val feeRateMasterMaestro = 0.006
    val feeRateMinMasterMaestro = 20
    val limitForNoFeeMasterMaestro = 75_000
    val monthlyLimit = 600_000
    val dailyLimit = 150_000
    val feeRateVisaMir = 0.0075
    val feeRateMinVisaMir = 35
    val dailyLimitVkpay = 15_000
    val monthlyLimitVkpay = 40_000

    return when (cardType) {
        "Visa", "Mir" -> if (transferAmount > dailyLimit ||
            (transferAmount + previousTransfersSumMonth) > monthlyLimit
        ) (-1) else
            (if ((transferAmount * feeRateVisaMir) > feeRateMinVisaMir) transferAmount * feeRateVisaMir
            else feeRateMinVisaMir)
        "Maestro", "Master" -> if (transferAmount > dailyLimit ||
            (transferAmount + previousTransfersSumMonth) > monthlyLimit
        ) (-1) else
            (if (previousTransfersSumMonth + transferAmount > limitForNoFeeMasterMaestro)
                (transferAmount * feeRateMasterMaestro + feeRateMinMasterMaestro) else 0)
        else -> if (transferAmount > dailyLimitVkpay ||
            transferAmount + previousTransfersSumMonth > monthlyLimitVkpay
        ) (-1) else 0
    }
}
