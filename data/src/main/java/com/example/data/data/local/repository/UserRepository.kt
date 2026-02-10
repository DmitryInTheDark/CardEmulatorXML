package com.example.data.data.local.repository

import android.content.Context
import android.content.SharedPreferences
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
    private val context: Context,
    private val userDB: UserDao,
    private val cardsDao: CardsDao
): AuthUseCase, CardsUseCase, ProfileUseCase {
    private val sharedPreferences = context.getSharedPreferences(USER_SHARED_PREF_NAME,
        Context.MODE_PRIVATE)

    fun saveUser(id: Int, secretKey: String){
        sharedPreferences.edit().apply{
            putInt(USER_ID_KEY, id)
            putString(USER_SECRET_KEY, secretKey)
            apply()
        }
    }

    override fun auth(login: String, password: String): UserModel {
        return Mappers.toUserModel(userDB.getUser(login, password))
    }

    override fun registration(name: String, email: String, password: String, secretKey: String, cardId: Int): UserModel {
        userDB.addUser(UserEntity(
            login = email,
            name = name,
            password = password,
            secretKey = secretKey,
            history = listOf(),
            cards = listOf(cardId),
        )).let { return Mappers.toUserModel(userDB.getUserById(it.toInt())) }
    }

    override fun createCard(name: String, style: Int, number: String): CardModel {
        cardsDao.addCard(
            CardEntity(style = style, number = number, name = name, dateExpired = "03/33" )
        ).let { return Mappers.toCardModel(cardsDao.getCard(it.toInt())) }
    }

    override fun deleteCard(cardId: Int) {
        TODO("Not yet implemented")
    }

    override fun pay(cardId: Int, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun getCards(): List<CardModel> {
        TODO("Not yet implemented")
    }

    override fun getCard(cardId: Int): CardModel {
        TODO("Not yet implemented")
    }

    override fun topUpCardBalance(cardId: Int, amount: Int) {
        TODO("Not yet implemented")
    }

    override fun changeName(): String {
        TODO("Not yet implemented")
    }

    override fun leaveAccount() {
        TODO("Not yet implemented")
    }

    companion object{
        private const val USER_SHARED_PREF_NAME = "users"
        private const val USER_ID_KEY = "user_id"
        private const val USER_SECRET_KEY = "user_secret"
    }
}