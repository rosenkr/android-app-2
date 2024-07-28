package se.umu.alro0113.trackandbet.marketdata.presentation.util.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.DefaultAlpha
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
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import se.umu.alro0113.trackandbet.marketdata.domain.model.Data
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

// TODO fix line thickness, remove charts vertical/horizontal layout lines
// TODO set axis min/max y values as dependent on the minimum and maximum of the 100 days closes, add a buffer of 10%
// TODO add shader ( see video ) and its like avanzas
// LineChart.targetVerticalAxisPosition ?

// TODO figure out if can do asynchronous work, as loading the graph is takming time
// TODO realization: Lets define a view scope, the chart recomposes when the view scope changes
// TODO so under the actual chart the user can choose "1 day" or "1 week" or "1 month"
// TODO and when they click it, the view scope changes. The view scope determines how zoomed out the chart is
// TODO but there is also the option for the user, through a dropdown button, to choose how many data points there is
// TODO per view scope. So the combination 1 month + 1 day will show about 23 ish END OF DAY IN MY CASE datapoints!
// VicoChart but currently has state and stuff in it
// Also right now its being built by a call to AAPL end of day ticker, 50 last days.
@Composable
fun VicoChart(last100ClosesData : List<Data>){
    // Dataset
    // hoist state elsewhere later, take it as param here, remember this VicoChart is just a component composable for the DetailScreen
    val modelProducer = remember { ChartEntryModelProducer() }
    val datasetForModel = remember { mutableStateListOf(listOf<FloatEntry>()) }
    val datasetLineSpec = remember{ arrayListOf<LineChart.LineSpec>()}
    val scrollState = rememberChartScrollState() // custom state for Vico Charts

    // Setting up modelProducer with dataset from the passed in state
    val dataPoints = arrayListOf<FloatEntry>() // currently last 50 days of data, so update once every weekday after market close
    datasetLineSpec.add(
        LineChart.LineSpec(
            lineThicknessDp = 2f,
            lineColor = MaterialTheme.colorScheme.primary.toArgb(), // expecting color val primaryLight = Color(0xFF4C662B)
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
    // For each day of eod Data, from long time ago (index 49...) to most recent day (index 0)
    val dateMap = mutableMapOf<Float, String>()
    for(i in last100ClosesData.indices.reversed()){
        val data = last100ClosesData[i]
        val xPos = (last100ClosesData.size - 1 - i).toFloat() // goes from 0 to 49, is x. Might need to cast to Float?
        val yPos = data.close.toFloat()
        dataPoints.add(FloatEntry(x = xPos, y = yPos))
        dateMap[xPos] = data.date // for ValueFormatter
    }

    datasetForModel.add(dataPoints)
    modelProducer.setEntries(datasetForModel)

    // TODO change this to be adaptive to the current tickers max and min values in 100 days
    val axisValuesOverrider = CustomAxisValuesOverrider(
        minY = 160f,  // Set your desired min value for y-axis
        maxY = 240f   // Set your desired max value for y-axis
    )
    // Creating the vico graph inside a Card
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(350.dp)
        .padding(16.dp, 16.dp, 16.dp),
    ) {
        Text(text = "AAPL last 100 trading days closing values")
        if(datasetForModel.isNotEmpty()){  // just-in-case guarding
            // styling the chart, see api/docs for customizing
            ProvideChartStyle {
                Chart(
                    modifier = Modifier.fillMaxSize(),
                    chart = lineChart(
                        lines = datasetLineSpec,
                        spacing = 3.dp,
                        axisValuesOverrider = axisValuesOverrider
                    ),
                    chartModelProducer = modelProducer,
                    startAxis = rememberStartAxis(
                        tickLength = 0.dp,
                        valueFormatter = { value, _ ->
                            value.toInt().toString()
                        },
                        itemPlacer = AxisItemPlacer.Vertical.default(
                            maxItemCount = 3
                        ),
                        guideline = null
                    ),

                    bottomAxis = rememberBottomAxis(
                        tickLength = 0.dp,
                        valueFormatter = { value, _ ->
                            convertToMyDate(dateMap[value]) // "25Mar", "15Apr" formats
                        },
                        itemPlacer = AxisItemPlacer.Horizontal.default(14), // 2 week interval
                        guideline = null // no lines horizontally
                    ),
                    isZoomEnabled = true,
                    chartScrollState = scrollState
                )
            }
        }
    }
}

