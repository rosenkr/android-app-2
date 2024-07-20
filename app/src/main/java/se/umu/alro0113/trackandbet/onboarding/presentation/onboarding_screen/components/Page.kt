package se.umu.alro0113.trackandbet.onboarding.presentation.onboarding_screen.components

import androidx.annotation.DrawableRes
import se.umu.alro0113.trackandbet.R

const val SAMPLE_TEXT = " heyyy "

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Title for page1 with text heyyy",
        description = SAMPLE_TEXT,
        // todo add images to resources/drawable as jpg's
        image = R.drawable.obs_1
    ),
    Page(
        title = "Title for page2 with text heyyy",
        description = SAMPLE_TEXT,
        image = R.drawable.obs_2
    ),
    Page(
        title = "Title for page3 with text heyyy",
        description = SAMPLE_TEXT,
        image = R.drawable.obs_3
    )
)