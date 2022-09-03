package com.simplekjl.trackit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Color.mid_blue,
    primaryVariant = Color.blue,
    secondary = Color.gray_4,
)

private val LightColorPalette = lightColors(
    primary = Color.mid_blue,
    primaryVariant = Color.blue,
    secondary = Color.gray_4,
    background = Color.white,
    surface = Color.white,
    onPrimary = Color.white,
    onSecondary = Color.black,
    onBackground = Color.black,
    onSurface = Color.black,
)

@Composable
fun TrackItTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}