package se.umu.alro0113.trackandbet.features.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import se.umu.alro0113.trackandbet.common.composables.MyBottomNavBar
import se.umu.alro0113.trackandbet.common.composables.MyTopAppBar
import se.umu.alro0113.trackandbet.common.util.BottomNavigationItem
import se.umu.alro0113.trackandbet.navigation.Screen

@Composable
internal fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()
    state?.let { HomeContent(state = it, navController = navController, viewModel = viewModel) }
}

// Placeholder content for displaying location data.
// Using activity context to get location data, result is a Task so we wrap
// it in a coroutine scope with IO dispatcher and await its result before sending update to Viewmodel
@Composable
fun HomeContent(state: HomeViewState, navController: NavHostController, viewModel : HomeViewModel) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val flpc : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    val clr: CurrentLocationRequest = CurrentLocationRequest.Builder()
        .setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
        .build()

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            scope.launch(Dispatchers.IO) {
                try{
                    val result: Location = flpc.getCurrentLocation(clr, null).await()
                    updateLocation(context, flpc)?.let { viewModel.setLocation(result) }
                } catch (e: SecurityException){
                    // not sure what to do. print to stack trace? Logcat? UI message to user?
                }
            }
        }
    }
    val items = listOf(
        BottomNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home, Screen.HomeScreen),
        BottomNavigationItem("Transactions", Icons.Filled.Analytics, Icons.Outlined.Analytics, Screen.TransactionsScreen),
        BottomNavigationItem("Tickers", Icons.Filled.Search, Icons.Outlined.Search, Screen.TickersScreen)
    )

    val myPosition = 0

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
            topBar = { MyTopAppBar(title = "Home") },
            bottomBar = {
                MyBottomNavBar(items = items, selectedItemIndex = myPosition, navController = navController)
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Button(onClick = { requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION) }
                ) {
                    Text(text = "Get Location")
                }

                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .border(1.dp, Color.White)
                        .padding(8.dp)
                ) {
                    Text(text = "Latitude: ${state.latitude}")
                    Text(text = "Longitude: ${state.longitude}")
                }
            }
        }
    }
}

// Used here and only here, but perhaps would be better to place it in another package
fun updateLocation(context: Context, flpc: FusedLocationProviderClient) : Task<Location>? {
    val clr: CurrentLocationRequest = CurrentLocationRequest.Builder()
        .setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
        .build()
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return null
    }
    val locationTask: Task<Location> = flpc.getCurrentLocation(clr, null)
    return locationTask
}