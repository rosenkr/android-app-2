package se.umu.alro0113.trackandbet.onboarding.presentation.onboarding_screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersContent
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersViewModel
import se.umu.alro0113.trackandbet.onboarding.presentation.OnboardingViewModel
import se.umu.alro0113.trackandbet.onboarding.presentation.onboarding_screen.components.NextBackButton
import se.umu.alro0113.trackandbet.onboarding.presentation.onboarding_screen.components.OnBoardingPage
import se.umu.alro0113.trackandbet.onboarding.presentation.onboarding_screen.components.PageIndicator
import se.umu.alro0113.trackandbet.onboarding.presentation.onboarding_screen.components.pages

@Composable
fun OnBoardingScreen(onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val currentPage = pagerState.currentPage
        val scope = rememberCoroutineScope()

        HorizontalPager(state = pagerState) { position ->
            OnBoardingPage(page = pages[position])
        }
        Spacer(modifier = Modifier.weight(1f))

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
                onGetStartedClick = { onboardingViewModel.saveAppEntry() })
        }
    }
}