package se.umu.alro0113.trackandbet.marketdata.domain.model

import kotlinx.serialization.Serializable

// How I imagine a Ticker is used, as most of the data that I deserialize in MarketStackApiResponses.kt is not of interest
// As it currently depends on the ticker api call with extended info such as price etc, and not on the exchanges/tickers api request,
// I will comment out them until I found a better solution. That way, I can proceed with the video

// SerializationException if this is not @Serializable
// But then JsonConvertException if made Serializable
// this implies misunderstanding in my code setup. See MarketStackApiResponses.kt
// It has the actual response for the api call. ExchangeResponse.
// All my api handling classes are currently working with this data class Ticker,
// but this class has nothing to do with the JSON structure that I actually get
// from the call. Notice how "Class "ExchangeResponse" is never used " warning in that file
data class Ticker(
    val name : String,
    val symbol : String,
    val has_intraday : Boolean,
    val has_eod : Boolean,
    /*val date : String,
    val exchange : String,
    val volume : Double,
    val close : Double,*/
)
