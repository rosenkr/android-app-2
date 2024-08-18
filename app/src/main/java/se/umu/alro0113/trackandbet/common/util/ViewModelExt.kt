package se.umu.alro0113.trackandbet.common.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// A video on YouTube showed this technique, although I implemented it in one place to send a Toast
// on network errors, I forgot to use it elsewhere
fun ViewModel.sendEvent(event: Any){
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}