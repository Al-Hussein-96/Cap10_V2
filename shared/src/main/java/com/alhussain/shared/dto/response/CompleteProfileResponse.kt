package com.alhussain.shared.dto.response

import com.alhussain.shared.dto.UserDto
import kotlinx.serialization.Serializable

@Serializable
data class CompleteProfileResponse(
    val user: UserDto,
)
