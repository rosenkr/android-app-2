package se.umu.alro0113.trackandbet.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object WelcomeScreen : Screen()

    @Serializable
    data object HomeScreen : Screen()

    // This screen takes arguments
    @Serializable
    data class DetailScreen(
        val symbol : String
    ) : Screen()

    @Serializable
    data object TickersScreen : Screen()
}
