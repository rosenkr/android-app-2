package se.umu.alro0113.trackandbet.marketdata.domain.model
import kotlinx.serialization.Serializable

// TODO break out to under data/remote/dto package instead?


@Serializable
data class DataResponse(
    val pagination: Pagination,
    val data: List<Data>
)

@Serializable
data class Data(
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val volume: Double,
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

