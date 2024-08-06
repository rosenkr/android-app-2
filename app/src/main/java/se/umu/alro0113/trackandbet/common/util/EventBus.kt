package se.umu.alro0113.trackandbet.common.util

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object EventBus {
    private val _events = Channel<Any>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: Any){
        _events.send(event)
    }
}

// Possibility to expand with more events
sealed interface Event {
    data class Toast(val message: String): Event
}