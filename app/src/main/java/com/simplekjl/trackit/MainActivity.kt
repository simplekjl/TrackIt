package com.simplekjl.trackit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.paddingFromBaseline
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
import com.simplekjl.ui.theme.TrackItColors
import com.simplekjl.ui.theme.TrackItTheme
import com.simplekjl.ui.theme.TrackItTypography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackItTheme {
                MainMenuOverflow()
            }
        }
    }
}

@Preview
@Composable
fun PreviewOverflowMenu() {
    MainMenuOverflow()
}

@Composable
fun MainMenuOverflow() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = { /*TODO jump tp the setting screen*/ }) {
                Icon(
                    Icons.Default.Settings,
                    tint = TrackItColors.white,
                    contentDescription = stringResource(R.string.menu_settings_action_description)
                )
            }
        },
        backgroundColor = TrackItColors.blue,
        contentColor = TrackItColors.gray_4,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun WeightValueElementTest() {
    WeightValueElement(
        metricNameRes = R.string.weight_current_label,
        weightValue = 74.0,
        colorRes = TrackItColors.plum
    )
}

@Composable
fun WeightValueElement(
    @StringRes metricNameRes: Int,
    weightValue: Double,
    colorRes: Color,
    modifier: Modifier = Modifier
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
