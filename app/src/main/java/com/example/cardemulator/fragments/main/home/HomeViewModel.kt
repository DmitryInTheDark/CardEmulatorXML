package com.example.cardemulator.fragments.main.home

import com.example.base.base.BasePagingViewModel
import com.example.base.base.BaseViewModel
import com.example.base.base.holder.CardHolderModel

class HomeViewModel: BasePagingViewModel<CardHolderModel>() {

    override val currentList = mutableListOf<CardHolderModel>()

}