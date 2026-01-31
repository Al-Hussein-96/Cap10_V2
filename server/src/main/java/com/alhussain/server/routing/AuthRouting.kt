package com.alhussain.server.routing

import com.alhussain.server.repositories.Users
import com.alhussain.server.repositories.Users.phoneNumber
import com.alhussain.server.service.AuthService
import com.alhussain.shared.ApiResponse
import com.alhussain.shared.dto.request.CompleteProfileRequest
import com.alhussain.shared.dto.request.SendOtpRequest
import com.alhussain.shared.dto.request.VerifyOtpRequest
import com.alhussain.shared.dto.response.CompleteProfileResponse
import com.alhussain.shared.dto.response.LogoutResponse
import com.alhussain.shared.dto.response.SendOtpResponse
import com.alhussain.shared.dto.response.VerifyOtpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.header
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun Route.authRoutes(authService: AuthService) {
    route("/api/auth") {
        /**
         * Send OTP to phone number
         * POST /api/auth/send-otp
         */
        // In your Ktor Route
//        post("/auth/login") {
//            val idToken =
//                call.request.header("Authorization")?.removePrefix("Bearer ")
//                    ?: return@post call.respond(HttpStatusCode.Unauthorized)
//
//            try {
//                // 1. Verify token authenticity with Firebase Admin SDK
//                val decodedToken: FirebaseToken = FirebaseAuth.getInstance().verifyIdToken(idToken)
//                val uid = decodedToken.uid
//                val phone = decodedToken.claims["phone_number"] as? String
//
//                // 2. Business Logic: Sync with Postgres using Exposed
//                transaction {
//                    val existingUser =
//                        Users.selectAll().where { Users.firebaseId eq uid }.singleOrNull()
//
//                    if (existingUser == null) {
//                        // First time login - create the user
//                        Users.insert {
//                            it[firebaseId] = uid
//                            it[phoneNumber] = phone ?: ""
//                        }
//                    }
//                }
//
//                call.respond(HttpStatusCode.OK, mapOf("message" to "Successfully authenticated"))
//            } catch (e: Exception) {
//                // Token was fake, expired, or tampered with
//                call.respond(HttpStatusCode.Unauthorized, "Invalid Token")
//            }
//        }

        post("/send-otp") {
            try {
                val request = call.receive<SendOtpRequest>()

                if (request.phoneNumber.isBlank()) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ApiResponse(
                            success = false,
                            message = "Phone number is required",
                            data = null,
                        ),
                    )
                    return@post
                }

                val result = authService.sendOtp(request.phoneNumber)

                call.respond(
                    HttpStatusCode.OK,
                    ApiResponse(
                        success = true,
                        message = "OTP sent successfully",
                        data = result,
                    ),
                )
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    ApiResponse<SendOtpResponse>(
                        success = false,
                        message = e.message ?: "Failed to send OTP",
                        data = null,
                    ),
                )
            }
        }

        /**
         * Verify OTP and login/register
         * POST /api/auth/verify-otp
         */
        post("/verify-otp") {
            try {
                val request = call.receive<VerifyOtpRequest>()

                if (request.phoneNumber.isBlank() || request.otpCode.isBlank()) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ApiResponse<VerifyOtpResponse>(
                            success = false,
                            message = "Phone number and OTP code are required",
                            data = null,
                        ),
                    )
                    return@post
                }

                val result = authService.verifyOtp(request.phoneNumber, request.otpCode)

                call.respond(
                    HttpStatusCode.OK,
                    ApiResponse(
                        success = true,
                        message = "OTP verified successfully",
                        data = result,
                    ),
                )
            } catch (e: IllegalArgumentException) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ApiResponse<VerifyOtpResponse>(
                        success = false,
                        message = e.message ?: "Invalid OTP",
                        data = null,
                    ),
                )
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    ApiResponse<VerifyOtpResponse>(
                        success = false,
                        message = e.message ?: "Verification failed",
                        data = null,
                    ),
                )
            }
        }

        /**
         * Complete user profile (name, age) - Protected route
         * POST /api/auth/complete-profile
         */
        authenticate("auth-jwt") {
            post("/complete-profile") {
                try {
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal?.payload?.getClaim("userId")?.asString()

                    if (userId == null) {
                        call.respond(
                            HttpStatusCode.Unauthorized,
                            ApiResponse<CompleteProfileResponse>(
                                success = false,
                                message = "Unauthorized",
                                data = null,
                            ),
                        )
                        return@post
                    }

                    val request = call.receive<CompleteProfileRequest>()

                    if (request.name.isBlank()) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            ApiResponse<CompleteProfileResponse>(
                                success = false,
                                message = "Name and age are required",
                                data = null,
                            ),
                        )
                        return@post
                    }

                    val result = authService.completeProfile(userId, request.name, request.age)

                    call.respond(
                        HttpStatusCode.OK,
                        ApiResponse(
                            success = true,
                            message = "Profile completed successfully",
                            data = result,
                        ),
                    )
                } catch (e: IllegalArgumentException) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ApiResponse<CompleteProfileResponse>(
                            success = false,
                            message = e.message ?: "Invalid input",
                            data = null,
                        ),
                    )
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        ApiResponse<CompleteProfileResponse>(
                            success = false,
                            message = e.message ?: "Failed to complete profile",
                            data = null,
                        ),
                    )
                }
            }

            /**
             * Logout - Protected route
             * POST /api/auth/logout
             */
            post("/logout") {
                try {
                    val principal = call.principal<JWTPrincipal>()
                    val userId = principal?.payload?.getClaim("userId")?.asString()

                    if (userId == null) {
                        call.respond(
                            HttpStatusCode.Unauthorized,
                            ApiResponse<LogoutResponse>(
                                success = false,
                                message = "Unauthorized",
                                data = null,
                            ),
                        )
                        return@post
                    }

                    authService.logout(userId)

                    call.respond(
                        HttpStatusCode.OK,
                        ApiResponse(
                            success = true,
                            message = "Logged out successfully",
                            data = LogoutResponse(success = true),
                        ),
                    )
                } catch (e: Exception) {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        ApiResponse<LogoutResponse>(
                            success = false,
                            message = e.message ?: "Logout failed",
                            data = null,
                        ),
                    )
                }
            }
        }
    }
}
