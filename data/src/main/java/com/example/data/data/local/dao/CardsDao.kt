package com.example.data.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.data.local.entities.CardEntity

@Dao
interface CardsDao {

    @Query("SELECT * FROM cards WHERE id = :cardId")
    fun getCard(cardId: Int): CardEntity

    @Insert(CardEntity::class)
    fun addCard(card: CardEntity): Long

    @Query("SELECT * FROM cards WHERE id IN (:cardIds)")
    fun getCardList(cardIds: List<Int>): List<CardEntity>

    @Query("SELECT * FROM cards")
    fun getAllCards(): List<CardEntity>

    @Update
    fun updateCard(card: CardEntity)
}