package se.umu.alro0113.trackandbet.marketdata.presentation.util.components

import se.umu.alro0113.trackandbet.marketdata.domain.model.Data

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