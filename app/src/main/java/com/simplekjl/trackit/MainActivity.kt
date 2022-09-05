package com.simplekjl.trackit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simplekjl.ui.theme.SampleData
import com.simplekjl.ui.theme.base.TrackItColors
import com.simplekjl.ui.theme.base.TrackItTheme
import com.simplekjl.ui.theme.components.ColorChartSection
import com.simplekjl.ui.theme.components.HomeSection
import com.simplekjl.ui.theme.components.LinearChartProgress
import com.simplekjl.ui.theme.components.TrackItMainToolbar
import com.simplekjl.ui.theme.components.WeightDetailsSection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackItTheme {
                HomeScreen()
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPReview() {
    TrackItTheme {
        HomeScreen()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TrackItMainToolbar(
            titleRes = R.string.app_name,
            menuSettingsDescRes = R.string.menu_settings_action_description
        )
    }) { paddingValues ->
        Column(
            modifier
                .verticalScroll(rememberScrollState())
                .height(400.dp)
                .padding(top = paddingValues.calculateTopPadding(), start = 16.dp, end = 16.dp, bottom = 8.dp)
        ) {
            HomeSection(title = null) {
                WeightDetailsSection(modifier = Modifier, currentWeight = 84.0, goalWeight = 70.0)
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
                        .height(400.dp),
                    weightValues = SampleData.entries,
                    topLimitLabel = com.simplekjl.ui.R.string.weight_progress_label,
                    bottomLimitLabel = com.simplekjl.ui.R.string.weight_progress_label, lineColor = TrackItColors.cucumber
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
