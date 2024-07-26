package se.umu.alro0113.trackandbet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import se.umu.alro0113.trackandbet.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen() // .setkeeponscreencondition ?
        enableEdgeToEdge()
        setContent {
            AppTheme {
                TrackAndBetApp()
            }
        }
    }
}
