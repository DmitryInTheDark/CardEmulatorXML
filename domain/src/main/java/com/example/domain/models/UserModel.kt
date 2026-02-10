package com.example.domain.models

data class UserModel(
    val id: Int,
    val name: String,
    val login: String,
    val password: String,
    val secretKey: String,
    val history: List<String>,
    val cards: List<Int>
)