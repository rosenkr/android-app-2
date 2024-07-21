package se.umu.alro0113.trackandbet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen.DetailScreen
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersScreen
import se.umu.alro0113.trackandbet.onboarding.presentation.OnboardingViewModel
import se.umu.alro0113.trackandbet.onboarding.presentation.home_screen.HomeScreen
import se.umu.alro0113.trackandbet.onboarding.presentation.onboarding_screen.OnBoardingScreen


// add screens for onboarding. see vid what is start destination (HomeScreen? add object and call it from MainAct)
// from the Screen class under onboarding.navigation we ave
//     OnBoardingScreen,
//    HomeScreen
// and ScreenA = TickersScreen
// ScreenB = DetailScreen
// notice how I need t
@Composable
fun AppNavigation(startDestination: Screen, onboardingViewModel: OnboardingViewModel) {
    val navController = rememberNavController()
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
        composable<Screen.OnBoardingScreen> {
            OnBoardingScreen(onboardingViewModel = onboardingViewModel)
        }
        composable<Screen.HomeScreen> {
            HomeScreen(navController = navController)
        }
    }
}


// for onboarding screens
/*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import se.umu.alro0113.trackandbet.onboarding.presentation.OnboardingViewModel
import androidx.navigation.compose.composable
import se.umu.alro0113.trackandbet.onboarding.presentation.home_screen.HomeScreen
import se.umu.alro0113.trackandbet.onboarding.presentation.onboarding_screen.OnBoardingScreen

/*
Currently just copy pasting from video, but overall,
my navigation for the project is different.
See the video from Lackner , or see main activity, where I use
objects for type safe compose navigation
 */

@Composable
fun AppNavigation(
    startDestination: String,
    onboardingViewModel: OnboardingViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.OnBoardingScreen.name) {
            OnBoardingScreen(onboardingViewModel = onboardingViewModel)
        }
        composable(route = Screen.HomeScreen.name) {
            HomeScreen() // missed adding this from video?
        }
    }
}
 */