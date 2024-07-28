package se.umu.alro0113.trackandbet.onboarding.presentation.welcome_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.onboarding.presentation.welcome_screen.components.NextBackButton
import se.umu.alro0113.trackandbet.onboarding.presentation.welcome_screen.components.OnBoardingPage
import se.umu.alro0113.trackandbet.onboarding.presentation.welcome_screen.components.PageIndicator
import se.umu.alro0113.trackandbet.onboarding.presentation.welcome_screen.components.pages


@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // State for list of 3 pages, with initial page 0
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val currentPage = pagerState.currentPage
        val scope = rememberCoroutineScope()

        HorizontalPager(state = pagerState) { position ->
            OnBoardingPage(page = pages[position])
        }
        Spacer(modifier = Modifier.weight(1f))

        // Page indicator and next-back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            PageIndicator(
                pageSize = pages.size,
                selectedPage = currentPage
            )

            NextBackButton(
                currentPage = currentPage,
                onNextClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(currentPage + 1)
                    }
                },
                onBackClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(currentPage - 1)
                    }
                },
                // Finish onboarding process by storing true in DataStore, and navigate after popping backstack
                onGetStartedClick = {
                    welcomeViewModel.saveOnBoardingState(true)
                    navController.popBackStack() // disallow back tracking to the onboarding screens
                    navController.navigate(Screen.HomeScreen)
                }
            )
        }
    }
}