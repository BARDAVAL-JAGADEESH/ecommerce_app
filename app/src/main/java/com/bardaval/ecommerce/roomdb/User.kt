package com.bardaval.ecommerce.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val email: String,
    val mobile: String,
    val username: String,
    val password: String
)
