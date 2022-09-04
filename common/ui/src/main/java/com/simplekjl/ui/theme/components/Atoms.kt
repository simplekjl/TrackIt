package com.simplekjl.ui.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    @StringRes metricNameRes: Int,
    weightValue: Double,
    colorRes: Color,
    modifier: Modifier = Modifier
        .size(height = 120.dp, width = 200.dp)
        .background(TrackItColors.gray_5)
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
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
