package com.simplekjl.trackit.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.simplekjl.trackit.R


val Roboto = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.roboto_black, FontWeight.Black),
    Font(R.font.roboto_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_thin_italic, FontWeight.Thin, FontStyle.Italic)
)


@Immutable
data class TrackItTypography(
    val h1: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 72.sp,
        lineHeight = 72.sp,
        letterSpacing = (-0.0208).em
    ),
    val h2: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 60.sp,
        lineHeight = 60.sp,
        letterSpacing = (-0.0083).em
    ),
    val h3: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 48.sp,
        lineHeight = 48.sp
    ),
    val h4: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 36.sp,
        lineHeight = 38.sp,
        letterSpacing = (0.007).em
    ),
    val h5: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.sp
    ),
    val h6: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 21.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        letterSpacing = (0.007).em
    ),
    val headline: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 22.sp
    ),
    val bodyStrong: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        letterSpacing = (0.0155).em
    ),
    val body: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        letterSpacing = (0.0155).em
    ),
    val subhead: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        letterSpacing = (0.007).em
    ),
    val text: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        letterSpacing = (0.007).em
    ),
    val footnote: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
        letterSpacing = (-0.006).em
    ),
    val caption: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        letterSpacing = (0.033).em
    ),
    val button: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        letterSpacing = (0.089).em
    ),
    val overline: TextStyle = TextStyle(
        fontFamily = Roboto,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 12.sp,
        letterSpacing = (0.15).em
    )
)