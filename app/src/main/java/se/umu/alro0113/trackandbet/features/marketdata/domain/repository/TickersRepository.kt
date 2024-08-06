package se.umu.alro0113.trackandbet.features.marketdata.domain.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.common.util.NetworkError
import se.umu.alro0113.trackandbet.features.marketdata.domain.model.Ticker

interface TickersRepository {

    // if the call goes wrong, return the left side of the Either
    // if the call goes right, return the right side of the Either
    suspend fun getTickers() : Either<NetworkError, List<Ticker>>
}