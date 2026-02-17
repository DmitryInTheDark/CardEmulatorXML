package com.example.data.data.local.repository

import com.example.data.data.local.entities.CardEntity
import com.example.data.data.local.entities.UserEntity
import com.example.domain.models.CardModel
import com.example.domain.models.UserModel

object Mappers {

    fun toUserModel(entity: UserEntity): UserModel{
        return UserModel(
            id = entity.id,
            name = entity.name,
            login = entity.login,
            password = entity.password,
            history = entity.history,
            cards = entity.cards
        )
    }

    fun toCardModel(entity: CardEntity): CardModel {
        return CardModel(
            id = entity.id,
            style = entity.style,
            number = entity.number,
            dateExpired = entity.dateExpired,
            name = entity.name
        )
    }
}