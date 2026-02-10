package com.example.data.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.data.local.converters.IntConverter
import com.example.data.data.local.converters.StringConverter

@Entity("users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("login")
    val login: String,
    @ColumnInfo("password")
    val password: String,
    @ColumnInfo("history")
    @field:TypeConverters(StringConverter::class)
    val history: List<String>,
    @ColumnInfo("cardsIds")
    @field:TypeConverters(IntConverter::class)
    val cards: List<Int>,
    @ColumnInfo("secretKey")
    val secretKey: String
)