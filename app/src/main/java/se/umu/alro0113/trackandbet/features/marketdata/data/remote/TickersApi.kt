package se.umu.alro0113.trackandbet.features.marketdata.data.remote

import de.jensklingenberg.ktorfit.http.GET
import se.umu.alro0113.trackandbet.BuildConfig
import se.umu.alro0113.trackandbet.features.marketdata.domain.model.ExchangeResponse

// TODO remove hardcoded exchange by adding query parameter, implemented as dropdown list of OMXS30, NQ100, SP500
interface TickersApi {

    @GET("exchanges/XNAS/tickers?access_key=" + BuildConfig.API_KEY)
    suspend fun getExchangeResponse(): ExchangeResponse
}