package com.simplekjl.trackit.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simplekjl.domain.model.Weight
import com.simplekjl.trackit.R
import com.simplekjl.ui.theme.SampleData
import com.simplekjl.ui.theme.base.TrackItColors
import com.simplekjl.ui.theme.clearFocusOnKeyboardDismiss
import com.simplekjl.ui.theme.components.AddDeleteFabButton
import com.simplekjl.ui.theme.components.BottomSheet
import com.simplekjl.ui.theme.components.ColorChartSection
import com.simplekjl.ui.theme.components.HomeSection
import com.simplekjl.ui.theme.components.LinearChartProgress
import com.simplekjl.ui.theme.components.TrackItMainToolbar
import com.simplekjl.ui.theme.components.WeightDetailsSection

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )
    val showModalSheet = rememberSaveable {
        mutableStateOf(false)
    }

    val weightsState = homeViewModel.weights.observeAsState()
    val profile = homeViewModel.profile.observeAsState()
    val initialWeight = Weight(0, date = System.currentTimeMillis(), 0.0, null)
    var startWeight by remember { mutableStateOf(initialWeight) }
    var currentWeight by remember { mutableStateOf(initialWeight) }
    var goalWeight by remember { mutableStateOf(0.0) }

    if (weightsState.value != null && profile.value != null) {
        if (weightsState.value?.isNotEmpty() == true) {
            startWeight = weightsState.value?.first() ?: initialWeight
            currentWeight = weightsState.value?.last() ?: initialWeight
        }
        goalWeight = profile.value?.goalWeight ?: 0.0
    }

    BottomSheet(
        modifier = Modifier.clearFocusOnKeyboardDismiss(),
        weight = initialWeight,
        modalBottomSheetState = sheetState,
        isSheetOpened = showModalSheet
    ) {
        Scaffold(
            topBar = {
                TrackItMainToolbar(
                    titleRes = R.string.app_name,
                    menuSettingsDescRes = R.string.menu_settings_action_description
                )
            },
            modifier = modifier.fillMaxSize(),
            floatingActionButton = {
                showModalSheet.value = true
                AddDeleteFabButton(
                    sheetState = sheetState, showModalSheet = showModalSheet
                )
            }
        ) { paddingValues ->
            Column(
                modifier
                    .verticalScroll(rememberScrollState())
                    .wrapContentHeight()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                HomeSection {
                    WeightDetailsSection(
                        modifier = Modifier,
                        startWeight = startWeight.weight,
                        currentWeight = currentWeight.weight,
                        goalWeight = goalWeight
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                HomeSection {
                    ColorChartSection(modifier = Modifier.fillMaxWidth())
                }
                Spacer(modifier = Modifier.height(16.dp))
                HomeSection {
                    LinearChartProgress(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp),
                        weightValues = SampleData.entries,
                        topLimitLabel = com.simplekjl.ui.R.string.weight_progress_label,
                        bottomLimitLabel = com.simplekjl.ui.R.string.weight_progress_label,
                        lineColor = TrackItColors.cucumber
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
