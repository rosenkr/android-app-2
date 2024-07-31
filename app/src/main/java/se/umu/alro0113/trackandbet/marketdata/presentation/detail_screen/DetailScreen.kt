package se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import se.umu.alro0113.trackandbet.marketdata.domain.model.Data
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.LoadingDialog
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.MyTopAppBar
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.VicoChart
import kotlin.reflect.full.memberProperties

@Composable
internal fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    symbol: String
) {
    LaunchedEffect(symbol) {
        viewModel.setSymbol(symbol)
    }
    val state by viewModel.state.observeAsState()
    state?.let { DetailContent(state = it) }
}

// End of day stock market details for a ticker, displays a chart and a card with information
@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    state: DetailsViewState
) {
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(title = "Details")
        }
    ) {
        Column (
            modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ){
            VicoChart(last100ClosesData = state.last100ClosesData)
            DetailsCard(details = state.details)
        }
    }
}

@Composable
fun DetailsCard(modifier: Modifier = Modifier, details: Data) {
    Card(
        modifier = modifier
            .padding(16.dp, 0.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            details::class.memberProperties.forEach { property ->
                val value = property.getter.call(details).toString()
                DetailItem(label = property.name.capitalize(), value = value)
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}

