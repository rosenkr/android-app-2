package se.umu.alro0113.trackandbet.onboarding.presentation.home_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable
import se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen.DetailScreen
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersScreen
import se.umu.alro0113.trackandbet.navigation.Screen


/* so will probably make this with object, then compose navigation calls this as startDest
    Note that this differs from Login Screen, which I will implement later
 */
/*
@Serializable
object HomeScreen*/

@Composable
fun HomeScreen(navController: NavHostController) {
    Log.d("HomeScreen", "HomeScreen recomposed")
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.TickersScreen)
                }
            ) {
                Text(text = "Go to tickers screen")
            }
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Home Screen",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

/*
force recomposition ? @Composable
fun ForceRecompositionTrigger() {
    val state by remember { mutableStateOf(Unit) }
    LaunchedEffect(state) {
        // This will force recomposition when the state changes
        // Use this for testing purposes
    }
}
 */