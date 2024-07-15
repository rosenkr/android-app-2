package se.umu.alro0113.trackandbet.marketdata.data.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.marketdata.data.mapper.toNetworkError
import se.umu.alro0113.trackandbet.marketdata.data.remote.TickersApi
import se.umu.alro0113.trackandbet.marketdata.domain.model.NetworkError
import se.umu.alro0113.trackandbet.marketdata.domain.model.Ticker
import se.umu.alro0113.trackandbet.marketdata.domain.repository.TickersRepository
import javax.inject.Inject

class TickersRepositoryImpl @Inject constructor(
    private val tickersApi: TickersApi
): TickersRepository {

    override suspend fun getTickers(): Either<NetworkError, List<Ticker>> {
        return Either.catch {
            tickersApi.getTickers()
        }.mapLeft { it.toNetworkError() }
    }
}