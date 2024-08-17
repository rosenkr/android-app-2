package se.umu.alro0113.trackandbet.common.util

import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.ChartEntryModel


// Used to set max and min values for the horizontal axis of a Vico chart, to be matched according to the associated datasets min and max values
class CustomAxisValuesOverrider(
    private val minY: Float,
    private val maxY: Float
) : AxisValuesOverrider<ChartEntryModel> {
    override fun getMinY(model: ChartEntryModel): Float {
        return minY
    }

    override fun getMaxY(model: ChartEntryModel): Float {
        return maxY
    }
}