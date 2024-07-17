package se.umu.alro0113.trackandbet.marketdata.presentation.detail_screen


import se.umu.alro0113.trackandbet.marketdata.domain.model.Data

data class DetailsViewState(
    val isLoading: Boolean = false,
    val details: Data = Data(0.0, 0.0, 0.0, 0.0, 0.0, "", "", ""),
    val error: String? = null
)