package se.umu.alro0113.trackandbet.marketdata.data.mapper

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import se.umu.alro0113.trackandbet.marketdata.domain.model.ApiError
import se.umu.alro0113.trackandbet.marketdata.domain.model.NetworkError
import java.io.IOException
fun Throwable.toNetworkError(): NetworkError {
    val error = when(this){
        is IOException -> ApiError.NetworkError
        is RedirectResponseException -> ApiError.UnknownResponse
        is ClientRequestException -> ApiError.UnknownResponse
        is ServerResponseException -> ApiError.UnknownResponse
        else -> ApiError.UnknownError
    }

    return NetworkError(
        error = error,
        t = this
    )
}