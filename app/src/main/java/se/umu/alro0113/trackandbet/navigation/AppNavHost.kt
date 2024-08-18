package se.umu.alro0113.trackandbet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import se.umu.alro0113.trackandbet.features.marketdata.presentation.detail_screen.DetailScreen
import se.umu.alro0113.trackandbet.features.marketdata.presentation.tickers_screen.TickersScreen
import se.umu.alro0113.trackandbet.features.onboarding.presentation.home_screen.HomeScreen
import se.umu.alro0113.trackandbet.features.onboarding.presentation.welcome_screen.WelcomeScreen
import se.umu.alro0113.trackandbet.features.transactions.presentation.TransactionsScreen

@Composable
fun AppNavHost(
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
            val args = it.toRoute<Screen.DetailScreen>()
            DetailScreen(symbol = args.symbol)
        }
        composable<Screen.WelcomeScreen> {
            WelcomeScreen(navController = navController)
        }
        composable<Screen.HomeScreen> {
            HomeScreen(navController = navController)
        }
        composable<Screen.TransactionsScreen> {
            TransactionsScreen(navController = navController)
        }
    }
}