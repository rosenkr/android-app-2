package se.umu.alro0113.trackandbet.marketdata.domain.model
// todo break out from feature hierarchy into common/util
data class NetworkError(
    val error: ApiError,
    val t: Throwable? = null
)

enum class ApiError(val message: String){
    NetworkError("Network Error"),
    UnknownResponse("Unknown Response"),
    UnknownError("Unknown Error")
}