package com.alhussain.cap10.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Primary Colors - Vibrant Football Green (Pitch)
val PrimaryLight = Color(0xFF2E7D32) // Deep grass green
val OnPrimaryLight = Color(0xFFFFFFFF)
val PrimaryContainerLight = Color(0xFFA5D6A7) // Light green
val OnPrimaryContainerLight = Color(0xFF003300)

val PrimaryDark = Color(0xFF81C784) // Bright green
val OnPrimaryDark = Color(0xFF003300)
val PrimaryContainerDark = Color(0xFF1B5E20) // Dark green
val OnPrimaryContainerDark = Color(0xFFC8E6C9)

// Secondary Colors - Electric Blue (Stadium lights, energy)
val SecondaryLight = Color(0xFF1565C0) // Vibrant blue
val OnSecondaryLight = Color(0xFFFFFFFF)
val SecondaryContainerLight = Color(0xFFBBDEFB) // Light blue
val OnSecondaryContainerLight = Color(0xFF001D36)

val SecondaryDark = Color(0xFF64B5F6) // Bright blue
val OnSecondaryDark = Color(0xFF001D36)
val SecondaryContainerDark = Color(0xFF0D47A1) // Deep blue
val OnSecondaryContainerDark = Color(0xFFE1F5FE)

// Tertiary Colors - Energetic Orange/Amber (Goals, excitement)
val TertiaryLight = Color(0xFFE65100) // Vibrant orange
val OnTertiaryLight = Color(0xFFFFFFFF)
val TertiaryContainerLight = Color(0xFFFFCCBC) // Light orange
val OnTertiaryContainerLight = Color(0xFF4A1504)

val TertiaryDark = Color(0xFFFFAB91) // Bright orange
val OnTertiaryDark = Color(0xFF4A1504)
val TertiaryContainerDark = Color(0xFFBF360C) // Deep orange
val OnTertiaryContainerDark = Color(0xFFFFE0B2)

// Error Colors
val ErrorLight = Color(0xFFD32F2F) // Red card red
val OnErrorLight = Color(0xFFFFFFFF)
val ErrorContainerLight = Color(0xFFFFCDD2)
val OnErrorContainerLight = Color(0xFF5F0000)

val ErrorDark = Color(0xFFEF5350)
val OnErrorDark = Color(0xFF5F0000)
val ErrorContainerDark = Color(0xFFB71C1C)
val OnErrorContainerDark = Color(0xFFFFEBEE)

// Background Colors
val BackgroundLight = Color(0xFFF8FBF8) // Very light green tint
val OnBackgroundLight = Color(0xFF191C19)

val BackgroundDark = Color(0xFF0E110E) // Very dark with green tint
val OnBackgroundDark = Color(0xFFE1E3E1)

// Surface Colors
val SurfaceLight = Color(0xFFF8FBF8)
val OnSurfaceLight = Color(0xFF191C19)
val SurfaceVariantLight = Color(0xFFDDE5DB)
val OnSurfaceVariantLight = Color(0xFF414941)

val SurfaceDark = Color(0xFF0E110E)
val OnSurfaceDark = Color(0xFFE1E3E1)
val SurfaceVariantDark = Color(0xFF414941)
val OnSurfaceVariantDark = Color(0xFFC1C9BF)

// Surface Container Colors (for cards, elevated surfaces)
val SurfaceContainerLowestLight = Color(0xFFFFFFFF)
val SurfaceContainerLowLight = Color(0xFFF2F5F2)
val SurfaceContainerLight = Color(0xFFECEFEC)
val SurfaceContainerHighLight = Color(0xFFE6E9E6)
val SurfaceContainerHighestLight = Color(0xFFE1E4E1)

val SurfaceContainerLowestDark = Color(0xFF090C09)
val SurfaceContainerLowDark = Color(0xFF171A17)
val SurfaceContainerDark = Color(0xFF1B1E1B)
val SurfaceContainerHighDark = Color(0xFF252825)
val SurfaceContainerHighestDark = Color(0xFF303330)

// Outline Colors
val OutlineLight = Color(0xFF717971)
val OutlineVariantLight = Color(0xFFC1C9BF)

val OutlineDark = Color(0xFF8B938A)
val OutlineVariantDark = Color(0xFF414941)

// Inverse Colors
val InverseSurfaceLight = Color(0xFF2E312E)
val InverseOnSurfaceLight = Color(0xFFF0F2EF)
val InversePrimaryLight = Color(0xFF81C784)

val InverseSurfaceDark = Color(0xFFE1E3E1)
val InverseOnSurfaceDark = Color(0xFF191C19)
val InversePrimaryDark = Color(0xFF2E7D32)

// Scrim
val Scrim = Color(0xFF000000)

// Light Color Scheme
val FootballLightColorScheme =
    lightColorScheme(
        primary = PrimaryLight,
        onPrimary = OnPrimaryLight,
        primaryContainer = PrimaryContainerLight,
        onPrimaryContainer = OnPrimaryContainerLight,
        secondary = SecondaryLight,
        onSecondary = OnSecondaryLight,
        secondaryContainer = SecondaryContainerLight,
        onSecondaryContainer = OnSecondaryContainerLight,
        tertiary = TertiaryLight,
        onTertiary = OnTertiaryLight,
        tertiaryContainer = TertiaryContainerLight,
        onTertiaryContainer = OnTertiaryContainerLight,
        error = ErrorLight,
        onError = OnErrorLight,
        errorContainer = ErrorContainerLight,
        onErrorContainer = OnErrorContainerLight,
        background = BackgroundLight,
        onBackground = OnBackgroundLight,
        surface = SurfaceLight,
        onSurface = OnSurfaceLight,
        surfaceVariant = SurfaceVariantLight,
        onSurfaceVariant = OnSurfaceVariantLight,
        surfaceContainerLowest = SurfaceContainerLowestLight,
        surfaceContainerLow = SurfaceContainerLowLight,
        surfaceContainer = SurfaceContainerLight,
        surfaceContainerHigh = SurfaceContainerHighLight,
        surfaceContainerHighest = SurfaceContainerHighestLight,
        outline = OutlineLight,
        outlineVariant = OutlineVariantLight,
        inverseSurface = InverseSurfaceLight,
        inverseOnSurface = InverseOnSurfaceLight,
        inversePrimary = InversePrimaryLight,
        scrim = Scrim,
    )

// Dark Color Scheme
val FootballDarkColorScheme =
    darkColorScheme(
        primary = PrimaryDark,
        onPrimary = OnPrimaryDark,
        primaryContainer = PrimaryContainerDark,
        onPrimaryContainer = OnPrimaryContainerDark,
        secondary = SecondaryDark,
        onSecondary = OnSecondaryDark,
        secondaryContainer = SecondaryContainerDark,
        onSecondaryContainer = OnSecondaryContainerDark,
        tertiary = TertiaryDark,
        onTertiary = OnTertiaryDark,
        tertiaryContainer = TertiaryContainerDark,
        onTertiaryContainer = OnTertiaryContainerDark,
        error = ErrorDark,
        onError = OnErrorDark,
        errorContainer = ErrorContainerDark,
        onErrorContainer = OnErrorContainerDark,
        background = BackgroundDark,
        onBackground = OnBackgroundDark,
        surface = SurfaceDark,
        onSurface = OnSurfaceDark,
        surfaceVariant = SurfaceVariantDark,
        onSurfaceVariant = OnSurfaceVariantDark,
        surfaceContainerLowest = SurfaceContainerLowestDark,
        surfaceContainerLow = SurfaceContainerLowDark,
        surfaceContainer = SurfaceContainerDark,
        surfaceContainerHigh = SurfaceContainerHighDark,
        surfaceContainerHighest = SurfaceContainerHighestDark,
        outline = OutlineDark,
        outlineVariant = OutlineVariantDark,
        inverseSurface = InverseSurfaceDark,
        inverseOnSurface = InverseOnSurfaceDark,
        inversePrimary = InversePrimaryDark,
        scrim = Scrim,
    )
