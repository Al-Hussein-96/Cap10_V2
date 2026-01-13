package com.alhussain.shared

import kotlinx.serialization.Serializable

// Generic API Response
@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T?,
)
