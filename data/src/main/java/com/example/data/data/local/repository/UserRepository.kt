package com.example.data.data.local.repository

import android.content.Context
import com.example.data.data.local.dao.CardsDao
import com.example.data.data.local.dao.UserDao
import com.example.data.data.local.entities.CardEntity
import com.example.data.data.local.entities.UserEntity
import com.example.domain.models.CardModel
import com.example.domain.models.UserModel
import com.example.domain.use_case.auth.AuthUseCase
import com.example.domain.use_case.cards.CardsUseCase
import com.example.domain.use_case.profile.ProfileUseCase
import javax.inject.Inject

class UserRepository @Inject constructor(
    context: Context,
    private val userDao: UserDao,
    private val cardsDao: CardsDao
): AuthUseCase, CardsUseCase, ProfileUseCase {
    private val sharedPreferences = context.getSharedPreferences(USER_SHARED_PREF_NAME,
        Context.MODE_PRIVATE)

    fun saveUser(id: Int){
        sharedPreferences.edit().apply{
            putInt(USER_ID_KEY, id)
            apply()
        }
    }

    override fun getCurrentUser(): UserModel? {
        val response = userDao.getUserById(sharedPreferences.getInt(USER_ID_KEY, -1))
        return if (response != null) Mappers.toUserModel(response) else null
    }

    override fun auth(login: String, password: String): UserModel? {
        val user = userDao.getUser(login, password)
        return if (user == null) null
        else {
            saveUser(user.id)
            Mappers.toUserModel(user)
        }
    }

    override fun registration(name: String, email: String, number: String, password: String, cardStyle: Int): UserModel {
        cardsDao.addCard(
            CardEntity(
                style = cardStyle,
                number = number,
                name = name,
                dateExpired = "03/33",
                amount = 0
            )
        ).let {
            userDao.addUser(UserEntity(
                login = email,
                name = name,
                password = password,
                history = listOf(),
                cards = listOf(it.toInt()),
            )).let { userId ->
                saveUser(userId.toInt())
                return Mappers.toUserModel(userDao.getUserById(userId.toInt())!!)
            }
        }
    }

    override fun checkEmails(email: String): Boolean {
        return userDao.getAllUsers().all { it.login != email }
    }

    override fun getAllUsers() {
        userDao.getAllUsers()
    }

    override fun createCard(name: String, style: Int, number: String): CardModel {
        cardsDao.addCard(
            CardEntity(style = style, number = number, name = name, dateExpired = "03/33", amount = 0 )
        ).let { return Mappers.toCardModel(cardsDao.getCard(it.toInt())) }
    }

    override fun deleteCard(cardId: Int) {
        TODO("Not yet implemented")
    }

    override fun pay(cardId: Int, amount: Int) {
        val card = cardsDao.getCard(cardId)
        val newAmount = card.amount - amount
        if (newAmount < 0){
            return
        }else{
            val newCard = card.copy(amount = newAmount)
            cardsDao.updateCard(newCard)
        }
    }

    override fun getCards(userId: Int): List<CardModel> {
        return cardsDao.getCardList(
            userDao.getUserById(userId)!!.cards
        ).map { Mappers.toCardModel(it) }
    }

    override fun getCard(cardId: Int): CardModel {
        return Mappers.toCardModel(cardsDao.getCard(cardId))
    }

    override fun topUpCardBalance(cardId: Int, amount: Int) {
        val card = cardsDao.getCard(cardId)
        val newAmount = card.amount + amount
        if (newAmount > 10000){
            return
        }else{
            val newCard = card.copy(amount = newAmount)
            cardsDao.updateCard(newCard)
        }
    }

    override fun isCardNumberExists(number: String): Boolean {
        return cardsDao.getAllCards().all { it.number != number }
    }

    override fun addCardToUser(cardId: Int) {
        val newCardList = userDao.getUserById(getCurrentUser()!!.id)!!.cards + cardId
        val newUser = userDao.getUserById(getCurrentUser()!!.id)?.copy(cards = newCardList)
        userDao.updateUser(newUser!!)
    }

    override fun changeName(): String {
        TODO("Not yet implemented")
    }

    override fun leaveAccount() {
        sharedPreferences.edit().apply{
            putInt(USER_ID_KEY, -1)
            apply()
        }
    }

    companion object{
        private const val USER_SHARED_PREF_NAME = "current_user"
        private const val USER_ID_KEY = "user_id"
    }
}