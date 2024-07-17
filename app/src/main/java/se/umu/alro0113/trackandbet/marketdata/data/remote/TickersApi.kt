package se.umu.alro0113.trackandbet.marketdata.data.remote

import de.jensklingenberg.ktorfit.http.GET
import se.umu.alro0113.trackandbet.marketdata.domain.model.ExchangeResponse

// TODO XNAS should ideally be an argument not precoded, if I add the ability to choose from different exchanges
// TODO could be implemented as having a dropdown list of typical exchanges in the MyTopAppBar.
// TODO also anothing thing that would be nice on the TickersScreen is a search bar that autofills, helps user by only showing those
// TODO tickers that have a substring of the current string in the field
interface TickersApi {
    @GET("exchanges/XNAS/tickers?access_key=5ebd15bbd5ba4ddfbfd10698ea343f13")
    //@GET("testing, avoiding unnecessary api calls")
    suspend fun getExchangeResponse(): ExchangeResponse

}