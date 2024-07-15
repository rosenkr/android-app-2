package se.umu.alro0113.trackandbet.marketdata.data.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.marketdata.data.mapper.toNetworkError
import se.umu.alro0113.trackandbet.marketdata.data.remote.TickersApi
import se.umu.alro0113.trackandbet.marketdata.domain.model.ExchangeResponse
import se.umu.alro0113.trackandbet.marketdata.domain.model.NetworkError
import se.umu.alro0113.trackandbet.marketdata.domain.model.Ticker
import se.umu.alro0113.trackandbet.marketdata.domain.repository.TickersRepository
import javax.inject.Inject

class TickersRepositoryImpl @Inject constructor(
    private val tickersApi: TickersApi
): TickersRepository {

    override suspend fun getTickers(): Either<NetworkError, List<Ticker>> {
        return Either.catch {
            val tickers = tickersApi.getExchangeResponse().data.tickers
            // temporary error checking if/else. The data IS being retrieved succesfully
            if (tickers.isNotEmpty()) {
                println("Tickers retrieved successfully. Count: ${tickers.size}")
            } else {
                println("No tickers retrieved or empty list.")
            }
            tickers // returning the list of tickers
            // tickersApi.getExchangeResponse().data.tickers
        }.mapLeft { it.toNetworkError() }
    }
}