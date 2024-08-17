package se.umu.alro0113.trackandbet.features.onboarding.presentation.home_screen


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import se.umu.alro0113.trackandbet.common.util.axisValueOverriderDayChart
import se.umu.alro0113.trackandbet.features.onboarding.domain.TickerData
import kotlin.random.Random
// Currently takes list of TickerData which is a property of the HomeViewState, and TickerData is the form for single objects of data in the response from the websocket server
@Composable
fun VicoChartDaily(dataList: List<TickerData>) {



    // Dataset
    val modelProducer = remember { ChartEntryModelProducer() }
    val datasetForModel = dataList.mapIndexed { index, data ->
        FloatEntry(x = index.toFloat(), y = data.c)
    }

    modelProducer.setEntries(listOf(datasetForModel))

    // Creating the vico graph inside a Card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(16.dp)
    ) {
        Text(text = "Apple")

        ProvideChartStyle {
            Chart(
                modifier = Modifier.fillMaxSize(),
                chart = lineChart(
                    lines(),
                    spacing = 1.dp,
                    axisValuesOverrider = axisValueOverriderDayChart(dataList)
                ),
                chartModelProducer = modelProducer,
                startAxis = rememberStartAxis(
                    tickLength = 0.dp,
                    valueFormatter = { value, _ -> value.toInt().toString() },
                    itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 5),
                    guideline = null
                ),
                bottomAxis = rememberBottomAxis(
                    tickLength = 0.dp,
                    itemPlacer = AxisItemPlacer.Horizontal.default(spacing = 15, offset = 0),
                    guideline = null
                ),
                isZoomEnabled = true,
                chartScrollState = rememberChartScrollState()
            )
        }
    }
}

// Helper to unclutter the main composable
@Composable
fun lines() : List<LineChart.LineSpec>{
    return listOf(
        LineChart.LineSpec(
            lineThicknessDp = 2f,
            lineColor = MaterialTheme.colorScheme.primary.toArgb(), // Line color
            lineBackgroundShader = DynamicShaders.fromBrush(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primaryContainer.copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                        MaterialTheme.colorScheme.primaryContainer.copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_END)
                    )
                )
            )
        )
    )
}