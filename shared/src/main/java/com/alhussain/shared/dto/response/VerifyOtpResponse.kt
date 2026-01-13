package com.alhussain.shared.dto.response

import com.alhussain.shared.dto.UserDto
import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpResponse(
    val token: String,
    val user: UserDto,
    val isNewUser: Boolean,
)
