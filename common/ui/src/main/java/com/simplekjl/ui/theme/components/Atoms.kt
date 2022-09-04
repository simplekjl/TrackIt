package com.simplekjl.ui.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.simplekjl.ui.R
import com.simplekjl.ui.theme.base.TrackItColors
import com.simplekjl.ui.theme.base.TrackItTheme
import com.simplekjl.ui.theme.base.TrackItTypography

@Preview
@Composable
fun PreviewOverflowMenu() {
    TrackItTheme {
        MainMenuOverflow(R.string.app_name, R.string.menu_setting_content_description)
    }
}

@Composable
fun MainMenuOverflow(
    @StringRes titleRes: Int,
    @StringRes menuSettingsDescRes: Int,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = titleRes)) },
        actions = {
            IconButton(onClick = { /*TODO jump tp the setting screen*/ }) {
                Icon(
                    Icons.Default.Settings,
                    tint = TrackItColors.white,
                    contentDescription = stringResource(menuSettingsDescRes)
                )
            }
        },
        backgroundColor = TrackItColors.blue,
        contentColor = TrackItColors.gray_4,
        elevation = 0.dp,
        modifier = modifier
    )
}

@Preview
@Composable
fun WeightValueElementTest() {
    TrackItTheme {
        WeightValueElement(
            metricNameRes = R.string.weight_current_label,
            weightValue = 74.0,
            colorRes = TrackItColors.plum
        )
    }
}

@Composable
fun WeightValueElement(
    modifier: Modifier = Modifier,
    @StringRes metricNameRes: Int,
    weightValue: Double,
    colorRes: Color
) {
    Column(
        modifier = modifier
            .size(height = 120.dp, width = 200.dp)
            .background(TrackItColors.gray_5),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = metricNameRes),
            style = TrackItTypography().h3,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
        // weight
        Text(
            text = weightValue.toString(),
            style = TrackItTypography().h4,
            color = colorRes,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Preview
@Composable
fun ColorDescriptionPreview() {
    TrackItTheme {
        Column {
            ColorDescription(
                descriptionRes = R.string.weight_goal_label,
                color = TrackItColors.mint
            )
            ColorDescription(
                descriptionRes = R.string.weight_lost_label,
                color = TrackItColors.cucumber
            )
        }
    }
}

@Composable
fun ColorDescription(
    modifier: Modifier = Modifier,
    @StringRes descriptionRes: Int,
    color: Color,
    shape: Shape = RoundedCornerShape(1.dp)
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .wrapContentWidth()
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(shape)
                .background(color)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = stringResource(id = descriptionRes),
            modifier = Modifier.padding(start = 8.dp), style = TrackItTypography().caption
        )
    }
}

@Preview
@Composable
fun LinearChartProgressTest() {
    val entries: MutableList<Entry> = mutableListOf()
    val c1e1 = Entry(0f, 100000f) // 0 == quarter 1
    entries.add(c1e1)
    val c1e2 = Entry(1f, 140000f) // 1 == quarter 2 ...
    entries.add(c1e2)

    // ...
    TrackItTheme {
        LinearChartProgress(
            weightValues = entries,
            topLimitLabel = R.string.app_name,
            bottomLimitLabel = R.string.weight_target_label,
            lineColor = TrackItColors.mint
        )
    }
}

@Composable
fun LinearChartProgress(
    modifier: Modifier = Modifier,
    weightValues: List<Entry> = emptyList(),
    @StringRes topLimitLabel: Int,
    @StringRes bottomLimitLabel: Int,
    lineColor: Color,
) {
    val topLabel = stringResource(id = topLimitLabel)
    val bottomLabel = stringResource(id = bottomLimitLabel)
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context -> LineChart(context) },
        update = { linearChart ->
            val dataSet = LineDataSet(weightValues, topLabel).apply {
                color = lineColor.toArgb()
            }
            val linearData = LineData(dataSet)
            linearChart.data = linearData
            // grid properties
            // https://stackoverflow.com/questions/31263097/mpandroidchart-hide-background-grid
//            linearChart.xAxis.isEnabled = false
            linearChart.axisRight.setDrawGridLines(false)
            linearChart.axisRight.isEnabled = false
            linearChart.xAxis.setDrawGridLines(false)
            linearChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

            linearChart.invalidate()
        }
    )
}
