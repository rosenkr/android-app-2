package se.umu.alro0113.trackandbet

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen.DetailScreen
import se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen.ScreenB
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.ScreenA
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersScreen
import se.umu.alro0113.trackandbet.onboarding.presentation.OnboardingViewModel
import se.umu.alro0113.trackandbet.ui.theme.AppTheme
import se.umu.alro0113.trackandbet.util.Event
import se.umu.alro0113.trackandbet.util.EventBus

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // figure out if I can change so this isnt here, in the vid it is, but I am using my other viewmodels contained elsewhere
    private val onboardingViewModel : OnboardingViewModel by viewModels<OnboardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            onboardingViewModel.isLoading
        } // ~~~~

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
                    // break this out in to a navigation package that
                    // is on the same level as the feature packages
                    // because in the vid he does
                    /*
                    AppNavigation(
                        start..
                        viewmodel ...
                        )
                     */
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenA // tickers screen, change name from ScreenA
                        // add HomeScreen and OnboardingScreen
                    ) {
                        composable<ScreenA> {
                            TickersScreen(navController)
                        }
                        composable<ScreenB> {
                            DetailScreen()
                        }
                    }
                }
            }
        }
    }
}