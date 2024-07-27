package se.umu.alro0113.trackandbet.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object WelcomeScreen : Screen()

    @Serializable
    object HomeScreen : Screen()

    // added argument for detail screen
    @Serializable
    data class DetailScreen(
        val symbol : String
    ) : Screen()

    @Serializable
    object TickersScreen : Screen()
}
