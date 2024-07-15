package se.umu.alro0113.trackandbet.marketdata.domain.model
import kotlinx.serialization.Serializable

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
    val tickers: List<Ticker>
)

@Serializable
data class Ticker(
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
