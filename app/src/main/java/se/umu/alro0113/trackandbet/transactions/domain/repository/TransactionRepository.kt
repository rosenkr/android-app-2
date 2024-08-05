package se.umu.alro0113.trackandbet.transactions.domain.repository

import arrow.core.Either
import se.umu.alro0113.trackandbet.marketdata.domain.model.NetworkError // TODO break out of marketdata feature hierarchy as it is general
import se.umu.alro0113.trackandbet.transactions.domain.model.Transaction

// for future use when the app has a remote server to feed transactions data from
// when the user asks for it by refreshing the screen with transactions
// or at certain set time intervals or when user enters the screen after some time
//
interface TransactionRepository {
    suspend fun getTransactions(
        fetchFromRemote: Boolean, // swipe down to refresh the list should refresh the database with new api call
        query: String? // TODO nullable? I have nullable some places, and some not
    ): Either<NetworkError, List<Transaction>>
}