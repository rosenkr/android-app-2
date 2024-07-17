package se.umu.alro0113.trackandbet.marketdata.data.remote
import de.jensklingenberg.ktorfit.http.GET
import se.umu.alro0113.trackandbet.marketdata.domain.model.DataResponse

// TODO aapl should be an argument, not precoded

interface DetailsApi {
    @GET("eod?access_key=5ebd15bbd5ba4ddfbfd10698ea343f13&symbols=AAPL")
    //@GET("testing, avoiding unnecessary api calls")
    suspend fun getDataResponse(): DataResponse

}