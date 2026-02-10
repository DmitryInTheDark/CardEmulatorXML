package com.example.data.data.local.converters

import androidx.room.TypeConverter

object StringConverter {

    @TypeConverter
    fun listStringToString(list: List<String>): String{
        return list.joinToString()
    }

    @TypeConverter
    fun stringToListString(string: String): List<String>{
        return string.split(",")
    }

}