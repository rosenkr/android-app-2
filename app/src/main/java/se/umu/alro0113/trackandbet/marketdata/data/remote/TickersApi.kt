package se.umu.alro0113.trackandbet.marketdata.data.remote

import de.jensklingenberg.ktorfit.http.GET
import se.umu.alro0113.trackandbet.marketdata.domain.model.ExchangeResponse

interface TickersApi {
    @GET("exchanges/XNAS/tickers?access_key=5ebd15bbd5ba4ddfbfd10698ea343f13")
    //@GET("testing, avoiding unnecessary api calls")
    suspend fun getExchangeResponse(): ExchangeResponse

}