package se.umu.alro0113.trackandbet.features.onboarding.data.dto

import kotlinx.serialization.Serializable
import se.umu.alro0113.trackandbet.features.onboarding.domain.TickerData

@Serializable
data class WebSocketMessage(
    val type: String, // "initial" or "update"
    val data: List<TickerData>? = null, // Only used for the initial message
    val item: TickerData? = null // Only used for update messages
)