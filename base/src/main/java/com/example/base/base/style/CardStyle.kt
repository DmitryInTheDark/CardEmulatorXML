package com.example.base.base.style

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.base.R

enum class CardStyle(
    @DrawableRes image: Int,
    @ColorRes textColor: Int
) {

    FEATURE(
        image = R.drawable.bg_card_1,
        textColor = R.color.white
    ),
    WINDOW(
        image = R.drawable.bg_card_2,
        textColor = R.color.white
    ),
    LIGHT(
        image = R.drawable.bg_card_3,
        textColor = R.color.black
    ),
    HEAVEN(
        image = R.drawable.bg_card_4,
        textColor = R.color.black
    ),
    CAT(
        image = R.drawable.bg_card_5,
        textColor = R.color.white
    ),
    MOON(
        image = R.drawable.bg_card_6,
        textColor = R.color.white
    )

}