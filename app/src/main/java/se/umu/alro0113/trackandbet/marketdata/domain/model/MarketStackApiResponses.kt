package se.umu.alro0113.trackandbet.marketdata.domain.model
import kotlinx.serialization.Serializable

// currently unused
@Serializable
data class TickerResponse(
    val pagination: Pagination,
    val data: List<TickerData>
)

@Serializable
data class TickerData(
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val volume: Double,
    val adj_high: Double,
    val adj_low: Double,
    val adj_close: Double,
    val adj_open: Double,
    val adj_volume: Double,
    val split_factor: Double,
    val dividend: Double,
    val symbol: String,
    val exchange: String,
    val date: String
)

@Serializable
data class ExchangeResponse(
    val pagination: Pagination,
    val data: ExchangeData
)

@Serializable
data class ExchangeData(
    val name: String,
    val acronym: String,
    val mic: String,
    val country: String,
    val city: String,
    val website: String,
    val tickers: List<TickerSummary>
)

@Serializable
data class TickerSummary(
    val name: String,
    val symbol: String,
    val has_intraday: Boolean,
    val has_eod: Boolean
)

@Serializable
data class Pagination(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val total: Int
)
