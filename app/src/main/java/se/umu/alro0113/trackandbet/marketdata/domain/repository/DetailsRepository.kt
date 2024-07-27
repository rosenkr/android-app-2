package se.umu.alro0113.trackandbet.marketdata.domain.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.marketdata.domain.model.NetworkError
import se.umu.alro0113.trackandbet.marketdata.domain.model.Data
import se.umu.alro0113.trackandbet.marketdata.domain.model.DataResponse

interface DetailsRepository {

    // if the call goes wrong, return the left side of the Either
    // if the call goes right, return the right side of the Either
    suspend fun getData() : Either<NetworkError, DataResponse> // TODO maybe change to getData(tickerSymbol) and then in the clickable card send in as param
}