package com.simplekjl.trackit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.simplekjl.ui.theme.base.TrackItTheme
import com.simplekjl.ui.theme.components.MainMenuOverflow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackItTheme {
                MainMenuOverflow(R.string.app_name, R.string.menu_settings_action_description)
            }
        }
    }
}
