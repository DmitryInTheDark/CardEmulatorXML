package com.example.domain.use_case.cards

import com.example.domain.models.CardModel

interface CardsUseCase {
    fun createCard(
        name: String,
        style: Int,
        number: String
    ): CardModel
    fun deleteCard(cardId: Int)
    fun pay(cardId: Int, amount: Int)
    fun getCards(userId: Int): List<CardModel>
    fun getCard(cardId: Int): CardModel
    fun topUpCardBalance(cardId: Int, amount: Int)
    fun isCardNumberExists(number: String): Boolean
    fun addCardToUser(cardId: Int)
}