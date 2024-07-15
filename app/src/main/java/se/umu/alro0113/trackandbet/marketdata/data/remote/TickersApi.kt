package se.umu.alro0113.trackandbet.marketdata.data.remote

import de.jensklingenberg.ktorfit.http.GET
import se.umu.alro0113.trackandbet.marketdata.domain.model.Ticker

interface TickersApi {

    @GET("exchanges/XNAS/tickers?access_key=5ebd15bbd5ba4ddfbfd10698ea343f13")
    suspend fun getTickers(): List<Ticker> // Change to return ExchangeResponse
    // and then elsewhere do getExchangeResponse.tickers to access the tickers
    /* something like:
    api = ktorfit.createTickersApi()..
    suspend fun fetchTickers(): List<Ticker> {
    return api.getExchangeResponse().tickers
}
     */
}