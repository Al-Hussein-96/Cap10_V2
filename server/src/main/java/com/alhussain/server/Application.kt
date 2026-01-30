package com.alhussain.server

import com.alhussain.server.repositories.OtpRepository
import com.alhussain.server.repositories.Otps
import com.alhussain.server.repositories.UserRepository
import com.alhussain.server.repositories.Users
import com.alhussain.server.routing.authRoutes
import com.alhussain.server.service.AuthService
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.google.firebase.auth.FirebaseAuth
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    // Database configuration
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/football_db", // environment.config.property("database.url").getString()
        driver = "org.postgresql.Driver", // environment.config.property("database.driver").getString()
        user = "alhussain", // environment.config.property("database.user").getString()
        password = "Pass@123", // environment.config.property("database.password").getString()
    )

    // Create tables if they don't exist
    transaction {
        SchemaUtils.create(Users, Otps)
    }

    // JWT Configuration
    val jwtSecret = "alhussain"
    val jwtIssuer = "ktor.io"

    // Install plugins
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            },
        )
    }

    install(Authentication) {
        jwt("auth-jwt") {
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer(jwtIssuer)
                    .build(),
            )
            validate { credential ->
                if (credential.payload.getClaim("userId").asString() != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(
                    io.ktor.http.HttpStatusCode.Unauthorized,
                    mapOf("success" to false, "message" to "Token is not valid or has expired"),
                )
            }
        }
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(
                io.ktor.http.HttpStatusCode.InternalServerError,
                mapOf("success" to false, "message" to (cause.message ?: "Internal server error")),
            )
        }
    }

    // Initialize repositories and services
    val userRepository = UserRepository()
    val otpRepository = OtpRepository()
    val authService =
        AuthService(
            userRepository = userRepository,
            otpRepository = otpRepository,
            jwtSecret = jwtSecret,
            jwtIssuer = jwtIssuer,
        )

    // Setup routing
    routing {
        authRoutes(authService)
    }
}

// fun main() {
//    embeddedServer(Netty, port = 5002, host = "0.0.0.0", module = Application::module)
//        .start(wait = true)
// }
//
// fun Application.module() {
//    install(ContentNegotiation) {
//        json()
//    }
//    install(CORS) {
//        allowHeader(HttpHeaders.ContentType)
//        allowMethod(HttpMethod.Delete)
//        // For ease of demonstration we allow any connections.
//        // Don't do this in production.
//        anyHost()
//    }
//
//    routing {
//        route("/tasks") {
//            get {
//                call.respond(Player("Mohammad Alhussain"))
//            }
//        }
//    }
// }
