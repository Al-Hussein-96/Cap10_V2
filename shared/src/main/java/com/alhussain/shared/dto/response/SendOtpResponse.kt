package com.alhussain.shared.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class SendOtpResponse(
    val phoneNumber: String,
    val expiresIn: Int,
    val otpCode: String? = null, // Only in dev environment
)
