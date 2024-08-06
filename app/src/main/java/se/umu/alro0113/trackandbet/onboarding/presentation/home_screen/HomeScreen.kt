package se.umu.alro0113.trackandbet.onboarding.presentation.home_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.MyTopAppBar
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.util.BottomNavigationItem
import se.umu.alro0113.trackandbet.util.MyBottomNavBar

@Composable
fun HomeScreen(navController: NavHostController) {
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
        ) {
            val padding = it // temp, todo add content for home screen
        }
    }
}
