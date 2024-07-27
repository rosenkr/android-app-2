package se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.marketdata.domain.repository.DetailsRepository
import se.umu.alro0113.trackandbet.marketdata.presentation.util.sendEvent
import se.umu.alro0113.trackandbet.util.Event

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
): ViewModel() {


    private val _state = MutableLiveData<DetailsViewState>()
    val state : LiveData<DetailsViewState> = _state

    init {
        _state.value = DetailsViewState()
        //getDetails(state.value!!.symbol) // meh..

    }
    fun setSymbol(symbol : String){
        viewModelScope.launch { _state.postValue(_state.value?.copy(symbol = symbol)) }
        getDetails(symbol)
    }
    // TODO should take ticker arg?
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