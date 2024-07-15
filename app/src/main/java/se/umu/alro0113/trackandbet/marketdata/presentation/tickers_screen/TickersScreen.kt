package se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.LoadingDialog
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.MyTopAppBar

@Composable
internal fun TickersScreen(
    viewModel: TickersViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()
    state?.let { TickersContent(state = it) }
}

@Composable
fun TickersContent(
    state: TickersViewState
) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(title = "Tickers")
        },
        containerColor = MaterialTheme.colorScheme.background, // not getting any green colors?
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            // more modifications here
        ) {
            items(state.tickers){ ticker ->
                Text(text = ticker.symbol,
                    color = MaterialTheme.colorScheme.onBackground, // not affecting
                    style = MaterialTheme.typography.headlineLarge) // text is changed tho
            }
        }
    }
}