package se.umu.alro0113.trackandbet.features.transactions.presentation

// used by ui to send events to its viewmodel for the transactions feature screen
// Original idea from Philipp Lackner on youtube
sealed class TransactionEvent {
    object Refresh: TransactionEvent()
    data class OnSearchQueryChange(val query: String): TransactionEvent()
}