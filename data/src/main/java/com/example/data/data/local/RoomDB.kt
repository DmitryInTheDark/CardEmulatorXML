package com.example.data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.data.local.dao.CardsDao
import com.example.data.data.local.dao.UserDao
import com.example.data.data.local.entities.CardEntity
import com.example.data.data.local.entities.UserEntity

@Database(entities = [UserEntity::class, CardEntity::class], version = 3, exportSchema = false)
abstract class RoomDB(): RoomDatabase()  {

    abstract val userDao: UserDao
    abstract val cardsDao: CardsDao

    companion object{

        const val DB_NAME = "database"

    }

}