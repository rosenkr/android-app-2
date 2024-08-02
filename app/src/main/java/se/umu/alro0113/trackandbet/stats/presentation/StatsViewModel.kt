package se.umu.alro0113.trackandbet.stats.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersViewState
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<StatsViewState>()
    val state : LiveData<StatsViewState> = _state

    init{
        _state.value = StatsViewState()

    }
}