package se.umu.alro0113.trackandbet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import se.umu.alro0113.trackandbet.features.onboarding.presentation.welcome_screen.SplashViewModel
import se.umu.alro0113.trackandbet.ui.theme.AppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Hilt performs constructor injection to provide SplashViewModel, which depends on a DataStoreRepository singleton provided by the AppModule.
    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // On app startup, display a splash screen with an Android default white icon
        installSplashScreen()

        enableEdgeToEdge()
        setContent {
            AppTheme {
                // The initial state of the screen is null. We need to wait for the ViewModel to set it to either
                // the Welcome screen or the Home screen before proceeding.
                val screen by splashViewModel.startDestination
                screen?.let { TrackAndBetApp(startDestination = it) }
            }
        }
    }
}
