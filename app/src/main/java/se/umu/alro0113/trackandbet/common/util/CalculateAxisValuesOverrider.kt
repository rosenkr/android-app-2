package se.umu.alro0113.trackandbet.common.util

import se.umu.alro0113.trackandbet.features.marketdata.domain.model.Data

// TODO this is just a temporary hardcoded quickfix to show a nice looking chart on Tickers -> Details
fun calculateAxisValuesOverrider(listOfData: List<Data>): CustomAxisValuesOverrider {
    val closeValues = listOfData.map { it.close.toFloat() }
    val minCloseValue = closeValues.minOrNull() ?: 0f
    val maxCloseValue = closeValues.maxOrNull() ?: 0f

    val minY = 0.9f * minCloseValue
    val maxY = 1.1f * maxCloseValue

    return CustomAxisValuesOverrider(
        minY = minY,
        maxY = maxY
    )
}