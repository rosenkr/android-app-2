package se.umu.alro0113.trackandbet.marketdata.data.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.marketdata.data.mapper.toNetworkError
import se.umu.alro0113.trackandbet.marketdata.data.remote.DetailsApi
import se.umu.alro0113.trackandbet.marketdata.domain.model.DataResponse
import se.umu.alro0113.trackandbet.marketdata.domain.model.NetworkError
import se.umu.alro0113.trackandbet.marketdata.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val detailsApi: DetailsApi
): DetailsRepository {

    override suspend fun getData(symbol: String): Either<NetworkError, DataResponse> {
        return Either.catch {
            val getRequestUrl = "eod?symbols=$symbol&access_key=5ebd15bbd5ba4ddfbfd10698ea343f13"
            val eodData = detailsApi.getDataResponse(getRequestUrl)
            eodData
        }.mapLeft {
            it.toNetworkError() }
    }
}