package com.example.domain.use_case.auth

import com.example.domain.models.UserModel

interface AuthUseCase {
    fun auth(login: String, password: String): UserModel?
    fun registration(name: String, email: String, number: String, password: String, cardStyle: Int): UserModel
    fun checkEmails(email: String): Boolean
    fun getAllUsers()
    fun getCurrentUser(): UserModel?
}