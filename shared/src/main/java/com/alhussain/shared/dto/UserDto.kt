package com.alhussain.shared.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val phoneNumber: String,
    val name: String?,
    val age: Int?,
    val isProfileComplete: Boolean,
)
