package se.umu.alro0113.trackandbet.transactions.presentation

sealed class TransactionEvent {
    object Refresh: TransactionEvent()
    data class OnSearchQueryChange(val query: String): TransactionEvent()
// TODO want to work with ints, convert elsewhere, here just use String
}