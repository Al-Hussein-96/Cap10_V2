package com.alhussain.server.service

// AuthService.kt

import com.alhussain.server.repositories.OtpRepository
import com.alhussain.server.repositories.UserRepository
import com.alhussain.shared.dto.UserDto
import com.alhussain.shared.dto.response.CompleteProfileResponse
import com.alhussain.shared.dto.response.SendOtpResponse
import com.alhussain.shared.dto.response.VerifyOtpResponse
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

class AuthService(
    private val userRepository: UserRepository,
    private val otpRepository: OtpRepository,
    private val jwtSecret: String,
    private val jwtIssuer: String,
) {
    private val otpExpiryMinutes: Long = 5

    /**
     * Send OTP to phone number
     */
    suspend fun sendOtp(phoneNumber: String): SendOtpResponse {
        val cleanPhone = cleanPhoneNumber(phoneNumber)

        // Generate 6-digit OTP
        val otpCode = generateOtp()

        // Calculate expiry time
        val expiresAt = LocalDateTime.now().plusMinutes(otpExpiryMinutes)

        // Save OTP to database
        otpRepository.create(cleanPhone, otpCode, expiresAt)

        // Send OTP via SMS service
        sendSms(cleanPhone, otpCode)

        return SendOtpResponse(
            phoneNumber = cleanPhone,
            expiresIn = otpExpiryMinutes.toInt(),
            // Remove otpCode in production
            otpCode = if (isDevelopment()) otpCode else null,
        )
    }

    /**
     * Verify OTP and create/login user
     */
    suspend fun verifyOtp(
        phoneNumber: String,
        otpCode: String,
    ): VerifyOtpResponse {
        val cleanPhone = cleanPhoneNumber(phoneNumber)

        // Find OTP record
        val otpRecord =
            otpRepository.findByPhoneNumber(cleanPhone)
                ?: throw IllegalArgumentException("OTP not found or expired")

        // Check if OTP is expired
        if (LocalDateTime.now().isAfter(otpRecord.expiresAt)) {
            throw IllegalArgumentException("OTP has expired")
        }

        // Check if OTP is already used
        if (otpRecord.isUsed) {
            throw IllegalArgumentException("OTP has already been used")
        }

        // Verify OTP code
        if (otpRecord.otpCode != otpCode) {
            throw IllegalArgumentException("Invalid OTP code")
        }

        // Mark OTP as used
        otpRepository.markAsUsed(otpRecord.id)

        // Check if user exists
        var user = userRepository.findByPhoneNumber(cleanPhone)

        if (user == null) {
            // Create new user
            user = userRepository.create(cleanPhone)
        }

        // Generate JWT token
        val token = generateToken(user.id)

        return VerifyOtpResponse(
            token = token,
            user =
                UserDto(
                    id = user.id,
                    phoneNumber = user.phoneNumber,
                    name = user.name,
                    age = user.age,
                    isProfileComplete = user.name != null && user.age != null,
                ),
            isNewUser = user.name == null || user.age == null,
        )
    }

    /**
     * Complete user profile
     */
    suspend fun completeProfile(
        userId: String,
        name: String,
        age: Int,
    ): CompleteProfileResponse {
        // Validate age
        if (age !in 13..100) {
            throw IllegalArgumentException("Invalid age")
        }

        // Update user profile
        val user =
            userRepository.update(userId, name.trim(), age)
                ?: throw IllegalArgumentException("User not found")

        return CompleteProfileResponse(
            user =
                UserDto(
                    id = user.id,
                    phoneNumber = user.phoneNumber,
                    name = user.name,
                    age = user.age,
                    isProfileComplete = true,
                ),
        )
    }

    /**
     * Logout user
     */
    suspend fun logout(userId: String) {
        // Implement token blacklisting if needed
        // For now, just a simple implementation
    }

    /**
     * Generate JWT token
     */
    private fun generateToken(userId: String): String {
        val expiresAt = Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000) // 30 days

        return JWT
            .create()
            .withIssuer(jwtIssuer)
            .withClaim("userId", userId)
            .withExpiresAt(expiresAt)
            .sign(Algorithm.HMAC256(jwtSecret))
    }

    /**
     * Generate 6-digit OTP
     */
    private fun generateOtp(): String = Random.nextInt(100000, 999999).toString()

    /**
     * Clean and validate phone number
     */
    private fun cleanPhoneNumber(phoneNumber: String): String {
        val cleaned = phoneNumber.replace(Regex("[^0-9]"), "")

        if (cleaned.length < 10) {
            throw IllegalArgumentException("Invalid phone number")
        }

        return cleaned
    }

    /**
     * Send SMS (integrate with your SMS provider)
     */
    private suspend fun sendSms(
        phoneNumber: String,
        otpCode: String,
    ) {
        // TODO: Integrate with SMS service (Twilio, AWS SNS, etc.)
        println("SMS sent to $phoneNumber: $otpCode")

        // Example with Twilio (add dependency):
        // val message = Message.creator(
        //     PhoneNumber(phoneNumber),
        //     PhoneNumber(twilioPhoneNumber),
        //     "Your verification code is: $otpCode"
        // ).create()
    }

    /**
     * Check if running in development environment
     */
    private fun isDevelopment(): Boolean = System.getenv("ENVIRONMENT") == "development"
}
