package com.simplekjl.trackit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.simplekjl.trackit.screen.home.HomeScreen
import com.simplekjl.ui.theme.base.TrackItTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackItTheme {
                HomeScreen(startWeight = 90.0, currentWeight = 80.4, goalWeight = 70.0)
            }
        }
    }
}
