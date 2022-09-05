package com.simplekjl.ui.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplekjl.ui.R
import com.simplekjl.ui.theme.base.TrackItColors
import com.simplekjl.ui.theme.base.TrackItTheme
import java.util.Locale

@Preview
@Composable
fun HomeSectionPreview() {
    TrackItTheme {
        HomeSection(title = R.string.app_name) {
            WeightDetailsSection(modifier = Modifier, currentWeight = 80.0, goalWeight = 65.0, startWeight = 90.0)
        }
    }
}

// -- Section for every part of the main screen
@Composable
fun HomeSection(
    @StringRes title: Int?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        title?.let {
            Text(
                stringResource(it).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                    .padding(horizontal = 16.dp)
            )
        }
        content() // calling the composable
    }
}

@Preview
@Composable
fun WeightDetailsSectionPreview() {
    TrackItTheme {
        WeightDetailsSection(modifier = Modifier, startWeight = 90.0, currentWeight = 74.0, goalWeight = 70.0)
    }
}

@Composable
fun WeightDetailsSection(
    modifier: Modifier,
    startWeight: Double,
    currentWeight: Double,
    goalWeight: Double
) {

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        WeightValueElement(
            metricNameRes = R.string.weight_start_label,
            weightValue = startWeight,
            colorRes = TrackItColors.banana
        )
        WeightValueElement(
            metricNameRes = R.string.weight_current_label,
            weightValue = currentWeight,
            colorRes = TrackItColors.plum
        )
        WeightValueElement(
            metricNameRes = R.string.weight_goal_label,
            weightValue = goalWeight,
            colorRes = TrackItColors.cucumber
        )
    }
}

@Preview
@Composable
fun ColorChartDescriptionPreview() {
    TrackItTheme {
        ColorChartSection()
    }
}

@Composable
fun ColorChartSection(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        ColorDescription(
            descriptionRes = R.string.weight_goal_label,
            color = TrackItColors.mint
        )
        Spacer(modifier = Modifier.padding(8.dp))
        ColorDescription(
            descriptionRes = R.string.weight_lost_label,
            color = TrackItColors.cucumber
        )
    }
}
