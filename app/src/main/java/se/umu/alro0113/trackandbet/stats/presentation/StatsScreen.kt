package se.umu.alro0113.trackandbet.stats.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersViewState
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.LoadingDialog
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.MyTopAppBar
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.util.BottomNavigationItem
import se.umu.alro0113.trackandbet.util.MyBottomNavBar

@Composable
internal fun StatsScreen(
    navController: NavHostController,
    viewModel: StatsViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()
    state?.let { StatsContent(state = it, navController = navController) }
}

@Composable
fun StatsContent(
    navController: NavHostController,
    state: StatsViewState
){
    val items = listOf(
        BottomNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home, Screen.HomeScreen),
        BottomNavigationItem("Stats", Icons.Filled.Analytics, Icons.Outlined.Analytics, Screen.StatsScreen),
        BottomNavigationItem("Tickers", Icons.Filled.Search, Icons.Outlined.Search, Screen.TickersScreen)
    )

    val myPosition = 1

    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(title = "My stats")
        },
        bottomBar = {
            MyBottomNavBar(items = items, navController = navController, selectedItemIndex = myPosition)
        }
    ) {

        // TODO stats content here. Think of what state (statistics of interest)
        // TODO such as ROI per "play" stats (buy/sell), averages, visuals...
        Column(modifier = Modifier.padding(top = it.calculateTopPadding())) {

        }
    }
}