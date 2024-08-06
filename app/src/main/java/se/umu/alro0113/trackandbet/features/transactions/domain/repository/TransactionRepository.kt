package se.umu.alro0113.trackandbet.features.transactions.domain.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.common.util.NetworkError
import se.umu.alro0113.trackandbet.features.transactions.domain.model.Transaction

interface TransactionRepository {
    suspend fun getTransactions(
        fetchFromRemote: Boolean,
        query: String?
    ): Either<NetworkError, List<Transaction>>
}