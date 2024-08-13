package se.umu.alro0113.trackandbet.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    actions: @Composable() (RowScope.() -> Unit) = {},
) {
    Column {
        GradientTop {
            TopAppBar(
                title = { Text(text = title) },
                colors = TopAppBarColors(containerColor = Color.Transparent, actionIconContentColor = Color.Red, navigationIconContentColor = Color.Black, scrolledContainerColor = Color.Black, titleContentColor = MaterialTheme.colorScheme.onSurface),
                actions = actions
            )
        }
        HorizontalDivider(thickness = 1.7.dp)
    }
}

// Top app bar gradient towards system top bar
@Composable
fun GradientTop(content: @Composable () -> Unit) {
    val topAppBarDefaultBackground = MaterialTheme.colorScheme.surfaceContainer

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surfaceBright,
                        topAppBarDefaultBackground
                    )
                )
            )
    ) {
        content()
    }
}