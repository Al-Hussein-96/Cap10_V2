package com.alhussain.shared.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class SendOtpRequest(
    val phoneNumber: String,
)
