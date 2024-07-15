package se.umu.alro0113.trackandbet.marketdata.presentation.tickers_screen

import se.umu.alro0113.trackandbet.marketdata.domain.model.Ticker

data class TickersViewState(
    val isLoading: Boolean = false,
    val tickers: List<Ticker> = emptyList(),
    val error: String? = null
)