package se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import kotlinx.serialization.Serializable
import se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen.DetailScreen
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.LoadingDialog
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.MyTopAppBar

// temp for testing compose navigation with safe args since ver 2.8.0, may break out the objects
// for screens into navigation.screens ?
@Serializable
object TickersScreen


@Composable
internal fun TickersScreen(
    navController: NavController,
    viewModel: TickersViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()
    state?.let { TickersContent(state = it, navController = navController) }
}

@Composable
fun TickersContent(
    navController: NavController,
    state: TickersViewState,
) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(title = "Tickers")
        }
    ) {

        LazyColumn(
            modifier = Modifier.padding(top = it.calculateTopPadding())
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
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(DetailScreen)
                // navigation works, but we also should simultaneously (asyncly) make api call somehwer
                // TODO check how/when the initial call for TickersScreen getTickers from api is <actually> made?
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Optional padding inside the Box
            contentAlignment = Alignment.Center // Center the content inside the Box
        ) {
            Text(
                text = symbol,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}