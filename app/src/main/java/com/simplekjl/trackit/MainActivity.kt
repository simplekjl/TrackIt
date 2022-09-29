package com.simplekjl.trackit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.simplekjl.trackit.screen.home.HomeScreen
import com.simplekjl.trackit.screen.home.HomeViewModel
import com.simplekjl.ui.theme.base.TrackItTheme
import org.koin.androidx.compose.viewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val manager = this.fragmentManager
        setContent {
            TrackItTheme {
                val homeViewModel: HomeViewModel by viewModel()
                HomeScreen(homeViewModel = homeViewModel)
            }
        }
    }
}
