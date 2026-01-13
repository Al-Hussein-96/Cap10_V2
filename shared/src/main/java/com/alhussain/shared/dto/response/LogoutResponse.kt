package com.alhussain.shared.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class LogoutResponse(
    val success: Boolean,
)
