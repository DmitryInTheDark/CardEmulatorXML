package com.example.domain.use_case.auth

import com.example.domain.models.UserModel

interface AuthUseCase {
    fun auth(login: String, password: String): UserModel
    fun registration(name: String, email: String, password: String, secretKey: String, cardId: Int): UserModel
}