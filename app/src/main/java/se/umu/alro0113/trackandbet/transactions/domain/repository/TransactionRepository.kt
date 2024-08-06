package se.umu.alro0113.trackandbet.transactions.domain.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.marketdata.domain.model.NetworkError
import se.umu.alro0113.trackandbet.transactions.domain.model.Transaction

interface TransactionRepository {
    suspend fun getTransactions(
        fetchFromRemote: Boolean,
        query: String?
    ): Either<NetworkError, List<Transaction>>
}