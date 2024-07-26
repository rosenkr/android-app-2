package se.umu.alro0113.trackandbet.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen.DetailScreen
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersScreen
import se.umu.alro0113.trackandbet.onboarding.presentation.OnboardingViewModel
import se.umu.alro0113.trackandbet.onboarding.presentation.home_screen.HomeScreen
import se.umu.alro0113.trackandbet.onboarding.presentation.onboarding_screen.OnBoardingScreen

@Composable
fun TrackAndBetNavHost(
    navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen
    ) {
        composable<Screen.TickersScreen> {
            TickersScreen(navController = navController)
        }
        composable<Screen.DetailScreen> {
            DetailScreen()
        }
        composable<Screen.OnBoardingScreen> {
            OnBoardingScreen()
        }
        composable<Screen.HomeScreen> {
            HomeScreen(navController = navController)
        }
    }
}