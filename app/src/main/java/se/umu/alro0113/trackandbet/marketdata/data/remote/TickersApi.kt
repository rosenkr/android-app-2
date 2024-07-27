package se.umu.alro0113.trackandbet.marketdata.data.remote

import de.jensklingenberg.ktorfit.http.GET
import se.umu.alro0113.trackandbet.BuildConfig
import se.umu.alro0113.trackandbet.marketdata.domain.model.ExchangeResponse

// TODO remove hardcoding of exchange, hide API key
interface TickersApi {

    @GET("exchanges/XNAS/tickers?access_key=" + BuildConfig.API_KEY)
    suspend fun getExchangeResponse(): ExchangeResponse
}