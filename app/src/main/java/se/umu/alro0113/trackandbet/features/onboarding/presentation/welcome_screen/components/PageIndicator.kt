package se.umu.alro0113.trackandbet.features.onboarding.presentation.welcome_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Three custom page indicators
@Composable
fun PageIndicator(
    pageSize: Int,
    selectedPage: Int
) {
    val primaryColor = MaterialTheme.colorScheme.primary

    Row (
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        repeat(pageSize) { page ->
            Box(modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(20.dp)
                .clip(CircleShape)
                .background(color =
                if (page == selectedPage) primaryColor else Color.LightGray)
            )
        }
    }
}