package se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import se.umu.alro0113.trackandbet.marketdata.domain.model.Data
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersContent
import se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.TickersViewModel
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.LoadingDialog
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.MyTopAppBar
import se.umu.alro0113.trackandbet.ui.theme.backgroundDark
import kotlin.reflect.full.memberProperties


// TODO not sure about the argument symbol. Keep in mind, currently I have hardcoded AAPL. Ideally, ScreenB is called with arg, or atleast the api call
// TODO should know which ticker to find details about, but perhaps I dont have to do ScreenB(theticker).
// TODO also, do I need to have navController here? In case the "back" button requires it for back stack entry stuff
@Serializable
object ScreenB

@Composable
internal fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()
    state?.let { DetailContent(state = it) }
}


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
            Surface(modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary) {
                // TODO generateVico(state) where the function is a composeable
            }

            DetailsCard(details = state.details)
        }
    }
}

@Composable
fun DetailsCard(modifier: Modifier = Modifier, details: Data) {
    Card(
        modifier = modifier
            .padding(10.dp)
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

