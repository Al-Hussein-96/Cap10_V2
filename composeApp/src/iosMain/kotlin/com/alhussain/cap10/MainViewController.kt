package com.alhussain.cap10

import androidx.compose.ui.window.ComposeUIViewController
import com.alhussain.cap10.screens.HomeScreen
import com.alhussain.cap10.screens.LoginScreen

fun mainViewController() = ComposeUIViewController { LoginScreen() }
