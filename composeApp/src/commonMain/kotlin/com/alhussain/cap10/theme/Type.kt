package com.alhussain.cap10.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cap10.composeapp.generated.resources.Res
import cap10.composeapp.generated.resources.cocon
import org.jetbrains.compose.resources.Font

// Set of Material typography styles to start with

@Composable
fun cap10Typography() = Typography(
    titleMedium = TextStyle(
        fontFamily = cap10Font(), fontWeight = FontWeight.Normal, fontSize = 16.sp
    )
)

@Composable
fun cap10Font() = FontFamily(
    Font(resource = Res.font.cocon, weight = FontWeight.Normal)
)

