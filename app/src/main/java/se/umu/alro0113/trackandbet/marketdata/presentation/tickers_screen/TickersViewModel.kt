package se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.marketdata.domain.repository.TickersRepository
import se.umu.alro0113.trackandbet.marketdata.presentation.util.sendEvent
import se.umu.alro0113.trackandbet.util.Event
import javax.inject.Inject

@HiltViewModel
class TickersViewModel @Inject constructor(
    private val tickersRepository: TickersRepository
): ViewModel() {

    private val _state = MutableLiveData<TickersViewState>()
    val state : LiveData<TickersViewState> = _state

    init {
        _state.value = TickersViewState()
        getTickers()
    }

    fun getTickers(){
        viewModelScope.launch {
            _state.postValue(_state.value?.copy(isLoading = true))

            tickersRepository.getTickers()
                .onRight { tickers ->
                    _state.postValue(_state.value?.copy(tickers = tickers, isLoading = false))
                    Log.d("TickersViewModel", "Tickers retrieved successfully. Count: ${tickers.size}")
                    Log.d("TickersViewModel", "Current state: $_state.value")
                }
                .onLeft { error ->
                    _state.postValue(_state.value?.copy(error = error.error.message, isLoading = false))
                    sendEvent(Event.Toast(error.error.message))
                }
        }
    }
}