package com.example.domain.models

data class CardModel(
    val id: Int,
    val style: Int,
    val number: String,
    val name: String,
    val dateExpired: String,
    val amount: Int
)