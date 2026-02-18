package com.example.base.base.holder

import com.example.base.base.interfaces.BaseHolderModel
import com.example.base.base.style.CardStyle

data class CardHolderModel(
    val style: CardStyle,
    val name: String,
    val number: String,
    val dateExpired: String
): BaseHolderModel

data class CardHolderModelWithId(
    val id: Int,
    val style: CardStyle,
    val name: String,
    val number: String,
    val dateExpired: String
): BaseHolderModel