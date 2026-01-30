package com.alhussain.server.repositories

// OtpRepository.kt

import com.alhussain.shared.model.Otp
import org.jetbrains.exposed.v1.core.*
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.less
import org.jetbrains.exposed.v1.javatime.CurrentDateTime
import org.jetbrains.exposed.v1.javatime.datetime
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update
import java.time.LocalDateTime
import java.util.*

object Otps : Table("otps") {
    val id = varchar("id", 36)
    val phoneNumber = varchar("phone_number", 20).index()
    val otpCode = varchar("otp_code", 6)
    val expiresAt = datetime("expires_at").index()
    val isUsed = bool("is_used").default(false).index()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)
}

class OtpRepository {
    /**
     * Create new OTP record
     */
    suspend fun create(
        phoneNumber: String,
        otpCode: String,
        expiresAt: LocalDateTime,
    ): Otp =
        transaction {
            // First, delete any existing OTPs for this phone number
            deleteByPhoneNumber(phoneNumber)

            val id = UUID.randomUUID().toString()
            val now = LocalDateTime.now()

            Otps.insert {
                it[Otps.id] = id
                it[Otps.phoneNumber] = phoneNumber
                it[Otps.otpCode] = otpCode
                it[Otps.expiresAt] = expiresAt
                it[isUsed] = false
                it[createdAt] = now
            }

            findById(id)!!
        }

    /**
     * Find OTP by ID
     */
    private fun findById(id: String): Otp? =
        transaction {
            Otps
                .selectAll()
                .where { Otps.id eq id }
                .singleOrNull()
                ?.toOtp()
        }

    /**
     * Find OTP by phone number (get most recent)
     */
    suspend fun findByPhoneNumber(phoneNumber: String): Otp? =
        transaction {
            Otps
                .selectAll()
                .where { Otps.phoneNumber eq phoneNumber }
                .orderBy(Otps.createdAt to SortOrder.DESC)
                .limit(1)
                .singleOrNull()
                ?.toOtp()
        }

    /**
     * Mark OTP as used
     */
    suspend fun markAsUsed(id: String): Boolean =
        transaction {
            Otps.update({ Otps.id eq id }) {
                it[isUsed] = true
            } > 0
        }

    /**
     * Delete OTPs by phone number
     */
    fun deleteByPhoneNumber(phoneNumber: String): Boolean =
        transaction {
            Otps.deleteWhere { Otps.phoneNumber eq phoneNumber }
            true
        }

    /**
     * Delete expired OTPs (cleanup job)
     */
    suspend fun deleteExpired(): Int =
        transaction {
            Otps.deleteWhere { expiresAt less LocalDateTime.now() }
        }

    /**
     * Delete all used OTPs (cleanup job)
     */
    suspend fun deleteUsed(): Int =
        transaction {
            Otps.deleteWhere { isUsed eq true }
        }

    /**
     * Convert ResultRow to Otp
     */
    private fun ResultRow.toOtp() =
        Otp(
            id = this[Otps.id],
            phoneNumber = this[Otps.phoneNumber],
            otpCode = this[Otps.otpCode],
            expiresAt = this[Otps.expiresAt],
            isUsed = this[Otps.isUsed],
            createdAt = this[Otps.createdAt],
        )
}
