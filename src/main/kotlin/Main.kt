fun main () {

    var result = calcFee(10000, "Visa")
    println(result)
}

fun calcFee (transferAmount:Int, cardType:String = "VK Pay", previousTransfersSum:Int = 0): Any {

    val feeRateMasterMaestro = 0.006
    val feeRateMinMasterMaestro = 20
    val limit = 75_000
    val feeRateVisaMir = 0.0075
    val feeRateMinVisaMir = 35

    return when (cardType) {
        "Visa" -> (if ((transferAmount * feeRateVisaMir) > feeRateMinVisaMir) transferAmount * feeRateVisaMir
        else feeRateMinVisaMir)
        "Mir" -> (if ((transferAmount * feeRateVisaMir) > feeRateMinVisaMir) transferAmount * feeRateVisaMir
        else feeRateMinVisaMir)
        "Maestro" -> (if (previousTransfersSum > limit) (transferAmount * feeRateMasterMaestro + feeRateMinMasterMaestro) else 0)

        "Master" -> (if (previousTransfersSum > limit) (transferAmount * feeRateMasterMaestro + feeRateMinMasterMaestro) else 0)

        else -> 0

    }
}
