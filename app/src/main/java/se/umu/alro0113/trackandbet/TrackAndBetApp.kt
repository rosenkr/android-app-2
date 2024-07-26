package se.umu.alro0113.trackandbet

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import se.umu.alro0113.trackandbet.navigation.TrackAndBetNavHost
import se.umu.alro0113.trackandbet.util.Event
import se.umu.alro0113.trackandbet.util.EventBus


@Composable
fun TrackAndBetApp() {
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val context = LocalContext.current
    LaunchedEffect(key1 = lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            EventBus.events.collect { event ->
                if (event is Event.Toast) {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val navController = rememberNavController()
    TrackAndBetNavHost(navController = navController)

}