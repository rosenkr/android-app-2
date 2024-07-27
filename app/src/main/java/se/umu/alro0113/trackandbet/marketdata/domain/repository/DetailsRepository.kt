package se.umu.alro0113.trackandbet.marketdata.domain.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.marketdata.domain.model.NetworkError
import se.umu.alro0113.trackandbet.marketdata.domain.model.DataResponse

interface DetailsRepository {

    // if the call goes wrong, return the left side of the Either
    // if the call goes right, return the right side of the Either
    suspend fun getData(symbol: String) : Either<NetworkError, DataResponse>
}