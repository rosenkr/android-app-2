package se.umu.alro0113.trackandbet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen.DetailScreen
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersScreen
import se.umu.alro0113.trackandbet.onboarding.presentation.home_screen.HomeScreen
import se.umu.alro0113.trackandbet.onboarding.presentation.welcome_screen.WelcomeScreen

@Composable
fun TrackAndBetNavHost(
    navController : NavHostController,
    startDestination : Screen
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.TickersScreen> {
            TickersScreen(navController = navController)
        }
        composable<Screen.DetailScreen> {
            DetailScreen()
        }
        composable<Screen.WelcomeScreen> {
            WelcomeScreen(navController = navController)
        }
        composable<Screen.HomeScreen> {
            HomeScreen(navController = navController)
        }
    }
}