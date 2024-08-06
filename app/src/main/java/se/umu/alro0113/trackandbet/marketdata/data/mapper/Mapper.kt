package se.umu.alro0113.trackandbet.marketdata.data.mapper

import android.util.Log
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import se.umu.alro0113.trackandbet.marketdata.domain.model.ApiError
import se.umu.alro0113.trackandbet.marketdata.domain.model.NetworkError
import java.io.IOException

// Map a Throwable to a NetworkError
fun Throwable.toNetworkError(): NetworkError {
    val TAG = "NetworkErrorLogger"
    Log.e(TAG, "Exception occurred: ${this.javaClass.simpleName}")
    Log.e(TAG, "Message: ${this.message}")
    Log.e(TAG, "Stack trace: ${this.stackTraceToString()}")
    val error = when(this){

        // TODO: add enum entries to ApiError that reflect the actually known exceptions RedirectResponse, ClientRequest and ServerResponse
        // TODO Which are essentiallly 300, 400, and 500 errors
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