package se.umu.alro0113.trackandbet.common.util

import se.umu.alro0113.trackandbet.features.marketdata.domain.model.Data
import se.umu.alro0113.trackandbet.features.onboarding.domain.TickerData

// TODO is actually an implementation for the marketdata detailscreen, may move this back to a more proper location
fun axisValueOverriderThreeMonthsChart(dataset: List<Data>): CustomAxisValuesOverrider {
    val closeValues = dataset.map { it.close.toFloat() }
    val minCloseValue = closeValues.minOrNull() ?: 0f
    val maxCloseValue = closeValues.maxOrNull() ?: 0f

    val minY = 0.9f * minCloseValue
    val maxY = 1.1f * maxCloseValue

    return CustomAxisValuesOverrider(
        minY = minY,
        maxY = maxY
    )
}

fun axisValueOverriderDayChart(dataset: List<TickerData>): CustomAxisValuesOverrider {
    val values = dataset.map { it.c } // price
    val minValue = values.minOrNull() ?: 230f // TODO hardcoded for apple but will need to rethink this
    val maxValue = values.maxOrNull() ?: 218f

    val minY = 0.98f * minValue
    val maxY = 1.02f * maxValue
    /*val minY = 240f
    val maxY = 200f*/

    return CustomAxisValuesOverrider(
        minY = minY,
        maxY = maxY
    )
}