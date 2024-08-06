package se.umu.alro0113.trackandbet.common.util

import se.umu.alro0113.trackandbet.features.marketdata.domain.model.Data

// TODO is actually an implementation for the marketdata detailscreen, may move this back to a more proper location
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