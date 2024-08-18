package se.umu.alro0113.trackandbet

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.navigation.AppNavHost
import se.umu.alro0113.trackandbet.common.util.Event
import se.umu.alro0113.trackandbet.common.util.EventBus

// Uses life cycle state to collect events for the eventbus and do something for each event on STARTED
// the launched effect is called when the lifecycleOwner changes.
@Composable
fun App(startDestination: Screen) {
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
    AppNavHost(startDestination = startDestination, navController = navController)
}