package se.umu.alro0113.trackandbet.marketdata.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.util.EventBus

fun ViewModel.sendEvent(event: Any){
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}