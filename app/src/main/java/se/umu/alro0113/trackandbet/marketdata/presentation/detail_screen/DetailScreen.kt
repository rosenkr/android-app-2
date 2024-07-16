package se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

/* should take a ticker symbol which it prints in a Text*/
/* together with viewmodel/state, handle the making of an api request for a specific ticker end of day
for example if tickersymbol = AAPL, http://api.marketstack.com/v1/eod
    ? access_key = 5ebd15bbd5ba4ddfbfd10698ea343f13
    & symbols = AAPL
 */

@Serializable
data class ScreenB(
    val symbol: String
)

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    symbol: String
) {
    Column (
        modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ){
        Surface(modifier.fillMaxWidth()) {
            // vico chart here
        }

        Card {
            // info about the current stock
        }
    }
}
