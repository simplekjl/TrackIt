package com.simplekjl.trackit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun TrackItTheme(
    colors: Colors = if (isSystemInDarkTheme()) darkColors() else lightColors(),
    typography: TrackItTypography = TrackItTypography(),
    shapes: Shapes = Shapes(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalShapes provides shapes
    ) {
        content()
    }
}

object MaterialTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: TrackItTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}

internal val LocalColors = staticCompositionLocalOf { lightColors() }

internal val LocalTypography = staticCompositionLocalOf { TrackItTypography() }

internal val LocalShapes = staticCompositionLocalOf { Shapes() }

//theming
fun lightColors(
    primary: androidx.compose.ui.graphics.Color = TrackItColors.mid_blue,
    primaryVariant: androidx.compose.ui.graphics.Color = TrackItColors.blue,
    background: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.White,
    secondary: androidx.compose.ui.graphics.Color = TrackItColors.gray_4
): Colors = Colors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondary,
    background = background,
    surface = TrackItColors.white,
    error = TrackItColors.plum,
    onPrimary = TrackItColors.white,
    onSecondary = TrackItColors.black,
    onBackground = TrackItColors.black,
    onError = TrackItColors.black,
    onSurface = TrackItColors.black,
    isLight = true
)

// TODO update the black theme colors
fun darkColors(
    primary: androidx.compose.ui.graphics.Color = TrackItColors.mid_blue,
    primaryVariant: androidx.compose.ui.graphics.Color = TrackItColors.blue,
    background: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.White,
    secondary: androidx.compose.ui.graphics.Color = TrackItColors.gray_4
): Colors = Colors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondary,
    background = background,
    surface = TrackItColors.white,
    error = TrackItColors.plum,
    onPrimary = TrackItColors.white,
    onSecondary = TrackItColors.white,
    onBackground = TrackItColors.black,
    onError = TrackItColors.black,
    onSurface = TrackItColors.black,
    isLight = false
)