package se.umu.alro0113.trackandbet.features.onboarding.presentation.welcome_screen.components

import androidx.annotation.DrawableRes
import se.umu.alro0113.trackandbet.R

// TODO replace current placeholders for the pages with content that fits the apps brand

const val SAMPLE_TEXT = " heyyy "

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Title for page1",
        description = SAMPLE_TEXT,
        image = R.drawable.obs_1
    ),
    Page(
        title = "Title for page2",
        description = SAMPLE_TEXT,
        image = R.drawable.obs_2
    ),
    Page(
        title = "Title for page3",
        description = SAMPLE_TEXT,
        image = R.drawable.obs_3
    )
)