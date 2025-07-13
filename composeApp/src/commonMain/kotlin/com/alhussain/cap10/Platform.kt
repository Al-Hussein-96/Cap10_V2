package com.alhussain.cap10

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform