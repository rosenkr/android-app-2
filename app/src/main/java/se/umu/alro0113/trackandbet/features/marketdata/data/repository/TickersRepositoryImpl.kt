package se.umu.alro0113.trackandbet.features.marketdata.data.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.common.util.toNetworkError
import se.umu.alro0113.trackandbet.features.marketdata.data.remote.TickersApi
import se.umu.alro0113.trackandbet.common.util.NetworkError
import se.umu.alro0113.trackandbet.features.marketdata.domain.model.Ticker
import se.umu.alro0113.trackandbet.features.marketdata.domain.repository.TickersRepository
import javax.inject.Inject

class TickersRepositoryImpl @Inject constructor(
    private val tickersApi: TickersApi
): TickersRepository {

    override suspend fun getTickers(): Either<NetworkError, List<Ticker>> {
        return Either.catch {
            val tickers = tickersApi.getExchangeResponse().data.tickers
            tickers
        }.mapLeft { it.toNetworkError() }
    }
}