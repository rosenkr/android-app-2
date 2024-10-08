package se.umu.alro0113.trackandbet.common.util

import android.util.Log
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import java.io.IOException

// Map a Throwable to a NetworkError
fun Throwable.toNetworkError(): NetworkError {
    val TAG = "NetworkErrorLogger"
    Log.e(TAG, "Exception occurred: ${this.javaClass.simpleName}")
    Log.e(TAG, "Message: ${this.message}")
    Log.e(TAG, "Stack trace: ${this.stackTraceToString()}")
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