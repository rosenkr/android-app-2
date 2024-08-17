package se.umu.alro0113.trackandbet.features.onboarding.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import se.umu.alro0113.trackandbet.common.composables.MyTopAppBar
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.common.util.BottomNavigationItem
import se.umu.alro0113.trackandbet.common.composables.MyBottomNavBar
import se.umu.alro0113.trackandbet.features.onboarding.domain.TickerData

@Composable
internal fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()
    state?.let { HomeContent(state = it, navController = navController) }
}

@Composable
fun HomeContent(state: HomeViewState, navController: NavHostController) {
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
            //val cValues = state.dataset.map { it.c }
            Box(modifier = Modifier
                .padding(it)
                .fillMaxSize()){

                //if(state.dataset.isNotEmpty()){
                    VicoChartDaily(dataList = state.dataset)
                //}

                /*
                LazyColumn(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Use 'items' with a list of Float
                    items(cValues) { cValue ->
                        Text(
                            text = cValue.toString(),  // Convert Float to String for display
                            modifier = Modifier.padding(vertical = 4.dp),
                        )
                    }
                }*/
            }
        }
    }
}
