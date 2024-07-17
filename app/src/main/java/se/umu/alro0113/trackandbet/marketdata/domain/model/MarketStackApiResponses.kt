package se.umu.alro0113.trackandbet.marketdata.domain.model
import kotlinx.serialization.Serializable

// TODO: add serializables for the response from http://api.marketstack.com/v1/eod?access_key=5ebd15bbd5ba4ddfbfd10698ea343f13&symbols=AAPL

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

