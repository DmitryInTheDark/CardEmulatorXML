package com.example.data.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE login = :login AND password = :password")
    fun getUser(login: String, password: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): UserEntity?

    @Insert(UserEntity::class, OnConflictStrategy.REPLACE)
    fun addUser(userEntity: UserEntity): Long

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserEntity>

    @Update
    fun updateUser(user: UserEntity)
}