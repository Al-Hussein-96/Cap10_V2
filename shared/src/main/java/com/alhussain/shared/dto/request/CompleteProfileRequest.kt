package com.alhussain.shared.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CompleteProfileRequest(
    val name: String,
    val age: Int,
)
