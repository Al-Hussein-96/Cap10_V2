package com.alhussain.shared.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequest(
    val phoneNumber: String,
    val otpCode: String,
)
