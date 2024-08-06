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

    // get all existing transactions from local storage or remote endpoint
    fun getTransactions(
        fetchFromRemote: Boolean = false,
        query: String = state.value?.searchQuery.toString() // string cant be null, but state can be. toString would make the string "null" if state is null
    ) {
        viewModelScope.launch {
            _state.postValue(_state.value?.copy(isLoading = true))

            // isRefreshing differs from isLoading in that isLoading is used for the LoadingDialog when TransactionsScreen is first navigated to
            // isRefreshing is true specifically when we are asked to fetch from remote
            if(fetchFromRemote){
                _state.postValue(_state.value?.copy(isRefreshing = true))
            }
            // the "delay", whether network or database fetching. but then isLoading  is same as isRefreshing?
            transactionRepository.getTransactions(fetchFromRemote, query)
                .onRight { transactions ->
                    _state.postValue(_state.value?.copy(transactions = transactions, isLoading = false, isRefreshing = false))
                }
                .onLeft { error ->
                    _state.postValue(_state.value?.copy(error = error.error.message, isLoading = false, isRefreshing = false))
                    sendEvent(Event.Toast(error.error.message))
                }
        }
    }
}