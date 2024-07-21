package se.umu.alro0113.trackandbet

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersScreen
import se.umu.alro0113.trackandbet.navigation.AppNavigation
import se.umu.alro0113.trackandbet.onboarding.presentation.OnboardingViewModel
import se.umu.alro0113.trackandbet.onboarding.presentation.home_screen.HomeScreen
import se.umu.alro0113.trackandbet.onboarding.presentation.onboarding_screen.OnBoardingScreen
import se.umu.alro0113.trackandbet.ui.theme.AppTheme
import se.umu.alro0113.trackandbet.util.Event
import se.umu.alro0113.trackandbet.util.EventBus

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // need to access viewmodel here to call installSplashScreen depending on isLoading or not
    private val onboardingViewModel : OnboardingViewModel by viewModels<OnboardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This needs to be called before Activity. setContentView or other view operations
        installSplashScreen().setKeepOnScreenCondition {
            onboardingViewModel.isLoading
        }

        enableEdgeToEdge()
        setContent {
            AppTheme {
                val lifecycle = androidx.lifecycle.compose.LocalLifecycleOwner.current.lifecycle
                LaunchedEffect(key1 = lifecycle) {
                    repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                        EventBus.events.collect { event ->
                            if (event is Event.Toast) {
                                Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(startDestination = onboardingViewModel.startDestination, onboardingViewModel = onboardingViewModel)
                    // TODO AppNavigation(startDestination = // I take an object, but onboardingViewModel.startDestination is an string.., onboardingViewModel = onboardingViewModel)
                }
            }
        }
    }
}