package se.umu.alro0113.trackandbet.features.marketdata.data.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.BuildConfig
import se.umu.alro0113.trackandbet.common.util.toNetworkError
import se.umu.alro0113.trackandbet.features.marketdata.data.remote.DetailsApi
import se.umu.alro0113.trackandbet.features.marketdata.domain.model.DataResponse
import se.umu.alro0113.trackandbet.common.util.NetworkError
import se.umu.alro0113.trackandbet.features.marketdata.domain.repository.DetailsRepository
import javax.inject.Inject


class DetailsRepositoryImpl @Inject constructor(
    private val detailsApi: DetailsApi
): DetailsRepository {

    override suspend fun getData(symbol: String): Either<NetworkError, DataResponse> {
        return Either.catch {
            val getRequestUrl = "eod?symbols=$symbol&access_key=" + BuildConfig.API_KEY
            val eodData = detailsApi.getDataResponse(getRequestUrl)
            eodData
        }.mapLeft {
            it.toNetworkError() }
    }
}