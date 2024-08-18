package se.umu.alro0113.trackandbet.features.marketdata.presentation.detail_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.common.util.Event
import se.umu.alro0113.trackandbet.common.util.sendEvent
import se.umu.alro0113.trackandbet.features.marketdata.domain.repository.DetailsRepository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
): ViewModel() {

    private val _state = MutableLiveData<DetailsViewState>()
    val state : LiveData<DetailsViewState> = _state

    init {
        Log.d("ViewModel", "detail ViewModel created: ${this.hashCode()}")
        _state.value = DetailsViewState()
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("ViewModel", "detail ViewModel cleared: ${this.hashCode()}")
    }

    fun setSymbol(symbol : String){
        viewModelScope.launch { _state.postValue(_state.value?.copy(symbol = symbol)) }
        getDetails(symbol)
    }

    fun getDetails(symbol : String){
        viewModelScope.launch {
            _state.postValue(_state.value?.copy(isLoading = true))

            detailsRepository.getData(symbol) //
                .onRight { details ->
                    _state.postValue(_state.value?.copy(details = details.data[0], isLoading = false, last100ClosesData = details.data))
                }
                .onLeft { error ->
                    _state.postValue(_state.value?.copy(error = error.error.message, isLoading = false))
                    sendEvent(Event.Toast(error.error.message))
                }
        }
    }
}