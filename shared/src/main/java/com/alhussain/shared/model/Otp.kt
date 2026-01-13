package com.alhussain.shared.model

import com.alhussain.shared.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Otp(
    val id: String,
    val phoneNumber: String,
    val otpCode: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val expiresAt: LocalDateTime,
    val isUsed: Boolean,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
)
