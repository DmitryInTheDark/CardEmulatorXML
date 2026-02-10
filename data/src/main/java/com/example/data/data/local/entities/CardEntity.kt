package com.example.data.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cards")
data class CardEntity(
    @PrimaryKey(true)
    val id: Int = 0,
    @ColumnInfo("style")
    val style: Int,
    @ColumnInfo("number")
    val number: String,
    @ColumnInfo("dateExpired")
    val dateExpired: String,
    @ColumnInfo("name")
    val name: String
)