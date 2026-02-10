package com.example.data.data.local.converters

import androidx.room.TypeConverter

object IntConverter {

    @TypeConverter
    fun listIntToString(list: List<Int>): String{
        return list.joinToString()
    }

    @TypeConverter
    fun stringToListInt(string: String): List<Int>{
        return string.split(",").map { it.toInt() }
    }

}