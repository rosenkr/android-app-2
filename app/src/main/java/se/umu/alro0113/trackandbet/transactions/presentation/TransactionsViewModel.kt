package se.umu.alro0113.trackandbet.transactions.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.marketdata.presentation.util.sendEvent
import se.umu.alro0113.trackandbet.transactions.domain.repository.TransactionRepository
import se.umu.alro0113.trackandbet.util.Event
import javax.inject.Inject


@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository // TODO implement
) : ViewModel() {

    private val _state = MutableLiveData<TransactionsViewState>()
    val state : LiveData<TransactionsViewState> = _state

    private var searchJob: Job? = null // do not want to make a query every character pressed, introduce delay

    init{
        _state.value = TransactionsViewState()
        getTransactions() // attempt call from database
        // but I dont want it on the init.. ? because whats the point of the database then?
    }

    fun onEvent(event: TransactionEvent) {
        when(event) {
            is TransactionEvent.Refresh -> {
                getTransactions(fetchFromRemote = true)
            }
            is TransactionEvent.OnSearchQueryChange -> {
                _state.value = _state.value?.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    getTransactions()
                }
            }
        }
    }

    // get all existing transactions in the cache or api
    fun getTransactions(
        fetchFromRemote: Boolean = false,
        query: String? = state.value?.searchQuery
    ) {
        viewModelScope.launch {
            _state.postValue(_state.value?.copy(isLoading = true))

            transactionRepository.getTransactions(fetchFromRemote, query) // TODO
                .onRight { transactions ->
                    _state.postValue(_state.value?.copy(transactions = transactions, isLoading = false))
                }
                .onLeft { error ->
                    _state.postValue(_state.value?.copy(error = error.error.message, isLoading = false))
                    sendEvent(Event.Toast(error.error.message))
                }
        }
    }
}