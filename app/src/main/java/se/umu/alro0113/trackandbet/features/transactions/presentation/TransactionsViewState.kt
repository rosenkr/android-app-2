package se.umu.alro0113.trackandbet.features.transactions.presentation

import se.umu.alro0113.trackandbet.features.transactions.domain.model.Transaction

data class TransactionsViewState(
    val isLoading: Boolean = false, // Arrow does not provide Loading state as the more Android UI-centric Resource does
    val error: String? = null, // to be used with Arrow & Event
    val transactions: List<Transaction> = emptyList(),
    val searchQuery: String = "",
    val isRefreshing: Boolean = false
)