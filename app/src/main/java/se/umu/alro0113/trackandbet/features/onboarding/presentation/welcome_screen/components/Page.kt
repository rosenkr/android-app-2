package se.umu.alro0113.trackandbet.features.onboarding.presentation.welcome_screen.components

import androidx.annotation.DrawableRes
import se.umu.alro0113.trackandbet.R


data class Page(
    val title: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "View US stocks",
        image = R.drawable.obs_4
    ),
    Page(
        title = "See historic data",
        image = R.drawable.obs_7
    ),
    Page(
        title = "See your transactions",
        image = R.drawable.obs_9
    )
)