package se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import se.umu.alro0113.trackandbet.marketdata.domain.model.Ticker

@Composable
fun TickerCard(
    modifier: Modifier = Modifier,
    ticker: Ticker
    ) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(contentColor = Color.White)
        ) {
        // would add AsyncImage in column here
        Text(text = ticker.name, style = MaterialTheme.typography.titleMedium)
    }
}