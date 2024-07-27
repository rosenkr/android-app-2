package se.umu.alro0113.trackandbet.marketdata.data.remote
import de.jensklingenberg.ktorfit.http.GET
import se.umu.alro0113.trackandbet.marketdata.domain.model.DataResponse

// TODO aapl should be an argument, not precoded
// TODO couldnt manage how to hide api key
// End of day details for AAPL
interface DetailsApi {
    @GET("eod?symbols=AAPL&access_key=5ebd15bbd5ba4ddfbfd10698ea343f13")
    suspend fun getDataResponse(): DataResponse

}