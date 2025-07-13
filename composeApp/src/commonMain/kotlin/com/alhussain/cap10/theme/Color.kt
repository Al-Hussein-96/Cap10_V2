package com.alhussain.cap10.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Teal200 = Color(0xFF03DAC5)
val Red400 = Color(0xFFCF6679)
val Green = Color(0xFF77af14)
val DarkBlue = Color(0xFF1F4F59)
val LightBlue = Color(0xFF54c0cc)
val IconColor = Color(0xFF606060)
val darkGray = Color(0x224C4964)

internal val colorPlates: ColorScheme = lightColorScheme(
    primary = DarkBlue,
    secondary = Green,
    error = Red400,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onError = Color.White,
    background = Color.White,
    onBackground = Red400,
    surface = Green,
    onSurface = Color.White,
    onSurfaceVariant = Color.White,
    onPrimaryContainer = Color.White,
)