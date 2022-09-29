package com.simplekjl.ui.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplekjl.ui.R
import com.simplekjl.ui.theme.base.TrackItColors
import com.simplekjl.ui.theme.base.TrackItTheme
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun HomeSectionPreview() {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    val showModalSheet = rememberSaveable {
        mutableStateOf(false)
    }
    TrackItTheme {
        HomeSection(titleRes = R.string.app_name) {
            WeightDetailsSection(
                modifier = Modifier,
                currentWeight = 80.0,
                goalWeight = 65.0,
                startWeight = 90.0,
                sheetState = sheetState,
                showModalSheet = showModalSheet
            )
        }
    }
}

// -- Section for every part of the main screen
@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int? = null,
    titleString: String? = null,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        val title = if (titleRes != null) stringResource(id = titleRes) else titleString
        title?.let {
            Text(
                it.uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
        }
        content() // calling the composable
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun WeightDetailsSectionPreview() {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    val showModalSheet = rememberSaveable {
        mutableStateOf(false)
    }
    TrackItTheme {
        WeightDetailsSection(
            modifier = Modifier,
            startWeight = 90.0,
            currentWeight = 74.0,
            goalWeight = 70.0,
            sheetState = sheetState,
            showModalSheet = showModalSheet
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeightDetailsSection(
    modifier: Modifier,
    startWeight: Double,
    startWeightClick: () -> Unit = {},
    currentWeight: Double,
    currentWeightClick: () -> Unit = {},
    goalWeight: Double,
    goalWeightClick: () -> Unit = {},
    sheetState: ModalBottomSheetState,
    showModalSheet: MutableState<Boolean>
) {
    val scope = rememberCoroutineScope()
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        WeightValueElement(
            metricNameRes = R.string.weight_start_label,
            weightValue = startWeight,
            colorRes = TrackItColors.mid_blue,
            onClick = {
                showModalSheet.value = !showModalSheet.value
                scope.launch { sheetState.show() }
                startWeightClick.invoke()
            },
            pluralsRes = R.string.kg_format
        )
        WeightValueElement(
            metricNameRes = R.string.weight_current_label,
            pluralsRes = R.string.kg_format,
            weightValue = currentWeight,
            colorRes = TrackItColors.plum,
            onClick = {
                showModalSheet.value = !showModalSheet.value
                scope.launch { sheetState.show() }
                currentWeightClick.invoke()
            }
        )
        WeightValueElement(
            metricNameRes = R.string.weight_goal_label,
            pluralsRes = R.string.kg_format,
            weightValue = goalWeight,
            colorRes = TrackItColors.cucumber,
            onClick = {
                showModalSheet.value = !showModalSheet.value
                scope.launch { sheetState.show() }
                goalWeightClick.invoke()
            }
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
