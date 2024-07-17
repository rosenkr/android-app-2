package se.umu.alro0113.trackandbet.marketdata.data.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.marketdata.data.mapper.toNetworkError
import se.umu.alro0113.trackandbet.marketdata.data.remote.DetailsApi
import se.umu.alro0113.trackandbet.marketdata.domain.model.NetworkError
import se.umu.alro0113.trackandbet.marketdata.domain.model.Data
import se.umu.alro0113.trackandbet.marketdata.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val detailsApi: DetailsApi
): DetailsRepository {

    // TODO must consider how vico wants full data array, while the card only wants .data[0]
    override suspend fun getData(): Either<NetworkError, Data> {
        return Either.catch {
            val eodData = detailsApi.getDataResponse().data[0] // the most recent end of day list of data
            eodData // returning the list of details
        }.mapLeft { it.toNetworkError() }
    }
}