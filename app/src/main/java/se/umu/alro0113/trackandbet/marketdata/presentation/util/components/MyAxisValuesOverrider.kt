package se.umu.alro0113.trackandbet.marketdata.presentation.util.components

import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.ChartEntryModel

class CustomAxisValuesOverrider(
    private val minY: Float,
    private val maxY: Float
) : AxisValuesOverrider<ChartEntryModel> {
    override fun getMinY(model: ChartEntryModel): Float? {
        return minY
    }

    override fun getMaxY(model: ChartEntryModel): Float? {
        return maxY
    }
}