package se.umu.alro0113.trackandbet.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object OnBoardingScreen : Screen()

    @Serializable
    object HomeScreen : Screen()

    @Serializable
    object DetailScreen : Screen()

    @Serializable
    object TickersScreen : Screen()
}
