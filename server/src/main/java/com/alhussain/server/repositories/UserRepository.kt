package com.alhussain.server.repositories

// UserRepository.kt

import com.alhussain.shared.model.User
import org.jetbrains.exposed.v1.core.Expression
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.like
import org.jetbrains.exposed.v1.javatime.CurrentDateTime
import org.jetbrains.exposed.v1.javatime.datetime
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update
import java.time.LocalDateTime
import java.util.UUID

object Users : Table("users") {
    val id = varchar("id", 36)
    val phoneNumber = varchar("phone_number", 20).uniqueIndex()
    val name = varchar("name", 100).nullable()
    val age = integer("age").nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)
}

class UserRepository {
    /**
     * Find user by phone number
     */
    suspend fun findByPhoneNumber(phoneNumber: String): User? =
        transaction {
            Users
                .selectAll()
                .where { Users.phoneNumber eq phoneNumber }
                .singleOrNull()
                ?.toUser()
        }

    /**
     * Find user by ID
     */
    fun findById(id: String): User? =
        transaction {
            Users
                .selectAll()
                .where { Users.id eq id }
                .singleOrNull()
                ?.toUser()
        }

    /**
     * Create new user
     */
    suspend fun create(
        phoneNumber: String,
        name: String? = null,
        age: Int? = null,
    ): User =
        transaction {
            val id = UUID.randomUUID().toString()
            val now = LocalDateTime.now()

            Users.insert {
                it[Users.id] = id
                it[Users.phoneNumber] = phoneNumber
                it[Users.name] = name
                it[Users.age] = age
                it[createdAt] = now
                it[updatedAt] = now
            }

            findById(id)!!
        }

    /**
     * Update user
     */
    suspend fun update(
        id: String,
        name: String? = null,
        age: Int? = null,
    ): User? =
        transaction {
            val updated =
                Users.update({ Users.id eq id }) {
                    if (name != null) it[Users.name] = name
                    if (age != null) it[Users.age] = age
                    it[updatedAt] = LocalDateTime.now()
                }

            if (updated > 0) findById(id) else null
        }

    /**
     * Delete user
     */
    suspend fun delete(id: String): Boolean =
        transaction {
            Users.deleteWhere { Users.id eq id } > 0
        }

    /**
     * Find all users
     */
    suspend fun findAll(
        limit: Int = 100,
        offset: Long = 0,
    ): List<User> =
        transaction {
            Users
                .selectAll()
                .orderBy(Users.createdAt to SortOrder.DESC)
                .limit(limit)
                .offset(offset)
                .map { it.toUser() }
        }

    /**
     * Search users by name
     */
    suspend fun searchByName(
        query: String,
        limit: Int = 20,
    ): List<User> =
        transaction {
            Users
                .selectAll()
                .where { Users.name like "%$query%" }
                .orderBy(Users.name to SortOrder.ASC)
                .limit(limit)
                .map { it.toUser() }
        }

    /**
     * Convert ResultRow to User
     */
    private fun ResultRow.toUser() =
        User(
            id = this[Users.id],
            phoneNumber = this[Users.phoneNumber],
            name = this[Users.name],
            age = this[Users.age],
            createdAt = this[Users.createdAt],
            updatedAt = this[Users.updatedAt],
        )
}
