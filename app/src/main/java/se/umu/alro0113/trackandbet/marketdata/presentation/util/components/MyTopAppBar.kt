package se.umu.alro0113.trackandbet.marketdata.presentation.util.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = Modifier.shadow(elevation = 5.dp),
    )
    HorizontalDivider()
}