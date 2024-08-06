package se.umu.alro0113.trackandbet.features.marketdata.presentation.tickers_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.features.marketdata.domain.repository.TickersRepository
import se.umu.alro0113.trackandbet.common.util.sendEvent
import se.umu.alro0113.trackandbet.common.util.Event
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
                }
                .onLeft { error ->
                    _state.postValue(_state.value?.copy(error = error.error.message, isLoading = false))
                    sendEvent(Event.Toast(error.error.message))
                }
        }
    }
}

