package se.umu.alro0113.trackandbet.marketdata.data.remote
import de.jensklingenberg.ktorfit.http.GET
import se.umu.alro0113.trackandbet.marketdata.domain.model.DataResponse

// TODO aapl should be an argument, not precoded
// TODO couldnt manage how to hide api key
interface DetailsApi {
    @GET("eod?symbols=AAPL&access_key=5ebd15bbd5ba4ddfbfd10698ea343f13") // oh so in AppModule, we have base url, then add this, THEN append parameter which is api key from object, which takes string from hidden gradle.properties?
    suspend fun getDataResponse(): DataResponse

}