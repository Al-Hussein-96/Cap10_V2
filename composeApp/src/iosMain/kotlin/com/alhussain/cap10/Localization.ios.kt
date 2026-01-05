package com.alhussain.cap10

actual fun getCurrentLanguage(): AvailableLanguages = AvailableLanguages.EN

actual fun getCurrentPlatform(): String = "iOS"
