package se.umu.alro0113.trackandbet.marketdata.data.remote
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Url
import se.umu.alro0113.trackandbet.marketdata.domain.model.DataResponse

// End of day details for a specific ticker
interface DetailsApi {
    @GET("")
    suspend fun getDataResponse(@Url url : String): DataResponse

}