package com.alhussain.cap10.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Constants (Screaming Snake Case)
private const val TEAL_VALUE = 0xFF03DAC5
private const val RED_VALUE = 0xFFCF6679
private const val GREEN_VALUE = 0xFF77af14
private const val DARK_BLUE_VALUE = 0xFF1F4F59
private const val LIGHT_BLUE_VALUE = 0xFF54C0CC
private const val ICON_COLOR_VALUE = 0xFF606060
private const val DARK_GRAY_VALUE = 0x224C4964

// Color objects (camelCase for variables is fine)
val teal200 = Color(TEAL_VALUE)
val red400 = Color(RED_VALUE)
val green = Color(GREEN_VALUE)
val darkBlue = Color(DARK_BLUE_VALUE)
val lightBlue = Color(LIGHT_BLUE_VALUE)
val iconColor = Color(ICON_COLOR_VALUE)
val darkGray = Color(DARK_GRAY_VALUE)

internal val colorPlates: ColorScheme =
    lightColorScheme(
        primary = darkBlue,
        secondary = green,
        error = red400,
        onPrimary = Color.White,
        onSecondary = Color.White,
        onError = Color.White,
        background = Color.White,
        onBackground = red400,
        surface = green,
        onSurface = Color.White,
        onSurfaceVariant = Color.White,
        onPrimaryContainer = Color.White,
    )
