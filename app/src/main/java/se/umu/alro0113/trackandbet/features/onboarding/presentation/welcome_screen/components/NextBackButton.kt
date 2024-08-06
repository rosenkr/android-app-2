package se.umu.alro0113.trackandbet.features.onboarding.presentation.welcome_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NextBackButton(
    currentPage: Int,
    onNextClick:() -> Unit,
    onBackClick:() -> Unit,
    onGetStartedClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (currentPage != 0){
            TextButton(onClick = { onBackClick() }) {
                Text(text = "Back")
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            if (currentPage == 2) {
                onGetStartedClick()
            } else {
                onNextClick()
            }
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = blueColor,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = if (currentPage == 2) "Get Started" else "Next")
        }
    }
}