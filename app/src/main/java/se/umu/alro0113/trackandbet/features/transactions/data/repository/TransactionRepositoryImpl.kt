package se.umu.alro0113.trackandbet.features.transactions.data.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.common.util.toNetworkError
import se.umu.alro0113.trackandbet.common.util.NetworkError
import se.umu.alro0113.trackandbet.database.AppDatabase
import se.umu.alro0113.trackandbet.features.transactions.data.mapper.toTransaction
import se.umu.alro0113.trackandbet.features.transactions.data.remote.TransactionsApi
import se.umu.alro0113.trackandbet.features.transactions.domain.model.Transaction
import se.umu.alro0113.trackandbet.features.transactions.domain.repository.TransactionRepository
import javax.inject.Inject
import javax.inject.Singleton

/* Implements the retrieval of Transaction objects from either local database cache or remote api */

@Singleton
class TransactionRepositoryImpl @Inject constructor(
    private val transactionsApi: TransactionsApi,
    db: AppDatabase
): TransactionRepository {

    private val dao = db.dao

    override suspend fun getTransactions(
        fetchFromRemote: Boolean,
        query: String?
    ): Either<NetworkError, List<Transaction>> {
        return Either.catch {
            val localTransactions = dao.searchTransaction(query)
            val transactions = localTransactions.map { it.toTransaction() }

            val isDbEmpty = localTransactions.isEmpty() && query == ""
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if(shouldJustLoadFromCache){
                transactions // return list from cache/db
            } else {
                val response = transactionsApi.getResponseBody()
                val transactionz = response.transactions
                dao.clearTransactions()
                dao.insertTransactions(transactionz.map { it.toTransaction() })
                transactionz // return list from remote/api
            }
        }.mapLeft {
            it.toNetworkError() }
    }
}