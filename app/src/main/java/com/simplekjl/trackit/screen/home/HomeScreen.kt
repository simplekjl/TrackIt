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
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplekjl.domain.model.Weight
import com.simplekjl.domain.usecase.NewWeightUseCase
import com.simplekjl.trackit.R
import com.simplekjl.ui.theme.SampleData
import com.simplekjl.ui.theme.base.TrackItColors
import com.simplekjl.ui.theme.base.TrackItTheme
import com.simplekjl.ui.theme.components.AddDeleteFabButton
import com.simplekjl.ui.theme.components.ColorChartSection
import com.simplekjl.ui.theme.components.HomeSection
import com.simplekjl.ui.theme.components.LinearChartProgress
import com.simplekjl.ui.theme.components.TrackItMainToolbar
import com.simplekjl.ui.theme.components.WeightDetailsSection
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

@Preview
@Composable
fun HomeScreenPReview() {
    TrackItTheme {
        HomeScreen(startWeight = 90.0, currentWeight = 80.4, goalWeight = 70.0)
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    startWeight: Double,
    currentWeight: Double,
    goalWeight: Double
) {
    val coroutineScope = rememberCoroutineScope()
    val useCase: NewWeightUseCase by inject(NewWeightUseCase::class.java)
    Scaffold(
        topBar = {
            TrackItMainToolbar(
                titleRes = R.string.app_name,
                menuSettingsDescRes = R.string.menu_settings_action_description
            )
        },
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            AddDeleteFabButton(onClick = {
                coroutineScope.launch {
                    useCase(
                        Weight(
                            1,
                            System.currentTimeMillis(),
                            74.0,
                            null
                        )
                    ).first()
                }
            })
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
            HomeSection(title = null) {
                WeightDetailsSection(
                    modifier = Modifier,
                    startWeight = startWeight,
                    currentWeight = currentWeight,
                    goalWeight = goalWeight
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            HomeSection(title = null) {
                ColorChartSection(modifier = Modifier.fillMaxWidth())
            }
            Spacer(modifier = Modifier.height(16.dp))
            HomeSection(title = null) {
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
