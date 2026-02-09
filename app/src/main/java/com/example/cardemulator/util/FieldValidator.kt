package com.example.cardemulator.util

import android.util.Patterns

object FieldValidator {

    fun isValidEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean{
        val regex = Regex("(?=\\S+$)." + "{6,35}$")
        return regex.matches(password)
    }
}