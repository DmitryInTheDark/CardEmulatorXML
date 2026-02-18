package com.example.cardemulator.util

import android.util.Patterns

object FieldValidator {

    fun isValidEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean{
        val regex = Regex("(?=\\S+$)." + "{6,35}$")
        return password.isNotEmpty() && regex.matches(password)
    }

    fun isValidName(name: String): Boolean{
        val regex = Regex("^[а-яА-ЯёЁ]+$")
        return name.isNotEmpty() && name.length >= 2 && name.length <= 15 && regex.matches(name)
    }

    fun isValidSecretKey(key: String): Boolean{
        return key.isNotEmpty() && key.length >= 5
    }
}