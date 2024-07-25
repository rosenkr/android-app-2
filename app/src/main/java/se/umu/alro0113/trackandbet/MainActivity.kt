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
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // need to access viewmodel here to call installSplashScreen depending on isLoading or not
    private val onboardingViewModel : OnboardingViewModel by viewModels<OnboardingViewModel>()
    private val TAG: String = "MainActivityLifeCycle" // TESTING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate") // TESTING

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
                    // use savedstatehandle to RESTORE onboardingViewModel.startDestination
                    val navController = rememberNavController()
                    AppNavigation(startDestination = onboardingViewModel.startDestination, onboardingViewModel = onboardingViewModel, navController = navController)
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
