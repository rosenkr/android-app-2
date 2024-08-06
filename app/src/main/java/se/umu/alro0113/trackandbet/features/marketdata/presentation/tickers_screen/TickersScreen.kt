package se.umu.alro0113.trackandbet.features.marketdata.presentation.tickers_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import se.umu.alro0113.trackandbet.common.composables.LoadingDialog
import se.umu.alro0113.trackandbet.common.composables.MyTopAppBar
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.common.util.BottomNavigationItem
import se.umu.alro0113.trackandbet.common.composables.MyBottomNavBar

@Composable
internal fun TickersScreen(
    navController: NavHostController,
    viewModel: TickersViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()
    state?.let { TickersContent(state = it, navController = navController) }
}

@Composable
fun TickersContent(
    navController: NavHostController,
    state: TickersViewState
) {
    val items = listOf(
        BottomNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home, Screen.HomeScreen),
        BottomNavigationItem("Transactions", Icons.Filled.Analytics, Icons.Outlined.Analytics, Screen.TransactionsScreen),
        BottomNavigationItem("Tickers", Icons.Filled.Search, Icons.Outlined.Search, Screen.TickersScreen)
    )

    val myPosition = 2

    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(title = "Tickers")
        },
        bottomBar = {
            MyBottomNavBar(items = items, navController = navController, selectedItemIndex = myPosition)
        }
    ) {

        LazyColumn(
            modifier = Modifier.padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding()) // pad both ends to see last item
        ) {

            items(state.tickers) { ticker ->
                ColumnItem(symbol = ticker.symbol, navController = navController)
            }
        }
    }
}

@Composable
fun ColumnItem(
    navController: NavController,
    modifier: Modifier = Modifier,
    symbol: String
) {

    Card(
        modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.DetailScreen(symbol))
            },
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = symbol,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}