package se.umu.alro0113.trackandbet.tradingassistance.domain.model
// currently just a chatgpt copy paste example
data class Strategy(
    val name: String,
    val stopLossPercentage: Double? = null, // E.g., 10% stop loss
    val takeProfitPercentage: Double? = null, // E.g., 20% take profit
    val bullishSeason: Map<String, Int>? = null, // E.g., {"winter" to 8, "summer" to 4} on a scale of 1-10
    val movingAveragePeriods: Int? = null, // E.g., 50-day moving average
    val rsiThresholds: Pair<Int, Int>? = null, // E.g., (30, 70) for RSI oversold and overbought thresholds
    val macdSettings: Triple<Int, Int, Int>? = null, // E.g., (12, 26, 9) for MACD (fast, slow, signal)
    val maxTradeDurationDays: Int? = null, // Maximum number of days to hold a trade
    val entryConditions: String? = null, // Custom entry conditions
    val exitConditions: String? = null, // Custom exit conditions
    val avoidFirstMinutes: Int? = 45 // Avoid trading in the first N minutes of market open
) {
    fun applyStopLoss(buyPrice: Double): Double? {
        return stopLossPercentage?.let { buyPrice * (1 - it / 100) }
    }

    fun applyTakeProfit(buyPrice: Double): Double? {
        return takeProfitPercentage?.let { buyPrice * (1 + it / 100) }
    }

    fun isBullishSeason(month: String): Boolean {
        return bullishSeason?.let {
            val score = it[month.toLowerCase()] ?: 5 // Default to 5 if month not specified
            score >= 6 // Define a threshold for being bullish
        } ?: false
    }

    fun isTradingAllowed(currentTime: Int): Boolean {
        return avoidFirstMinutes?.let { currentTime > it } ?: true
    }

    fun calculateMACD(fastMA: Double, slowMA: Double, signalLine: Double): Double {
        return fastMA - slowMA - signalLine
    }

    // Additional methods and logic can be added here
}
