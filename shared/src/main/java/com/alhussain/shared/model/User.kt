package com.alhussain.shared.model

import com.alhussain.shared.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class User(
    val id: String,
    val phoneNumber: String,
    val name: String?,
    val age: Int?,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val updatedAt: LocalDateTime,
)
