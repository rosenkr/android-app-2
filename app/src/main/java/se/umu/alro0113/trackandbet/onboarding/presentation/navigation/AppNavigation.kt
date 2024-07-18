package se.umu.alro0113.trackandbet.onboarding.presentation.navigation

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