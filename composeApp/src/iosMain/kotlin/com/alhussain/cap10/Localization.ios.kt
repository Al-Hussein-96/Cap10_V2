package com.alhussain.cap10

import com.alhussain.cap10.AvailableLanguages


actual fun getCurrentLanguage(): AvailableLanguages = AvailableLanguages.EN

actual fun getCurrentPlatform(): String = "iOS"