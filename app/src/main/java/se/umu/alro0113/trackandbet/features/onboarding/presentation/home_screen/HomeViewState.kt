package se.umu.alro0113.trackandbet.features.onboarding.presentation.home_screen

import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import se.umu.alro0113.trackandbet.features.onboarding.domain.TickerData

data class HomeViewState(
    val needOldData: Boolean = true,
    val dataset: List<TickerData> = emptyList(),
    // should maybe put webSocketSession as private property in vm..
    val webSocketSession: DefaultClientWebSocketSession? = null, // open/close functions in VM needs access to this
    val error: String? = null // TODO if to be used with arrow either for sending snackbars/toasts with error messages when websocket/IO exceptions caught
)

