package com.example.cardemulator.fragments.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.base.BasePagingViewModel
import com.example.base.base.holder.CardHolderModel
import com.example.base.base.style.CardStyle
import com.example.domain.models.CardModel
import com.example.domain.models.UserModel
import com.example.domain.use_case.auth.AuthUseCase
import com.example.domain.use_case.cards.CardsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val cardsUseCase: CardsUseCase,
    private val authUseCase: AuthUseCase
): BasePagingViewModel<CardHolderModel>() {

    override val currentList = mutableListOf<CardHolderModel>()
    private val _state = MutableLiveData<State>(State.SimpleState())
    val state: LiveData<State> = _state

    fun initScreen(){
        viewModelScope.launch(Dispatchers.IO) {
            currentList.addAll(
                cardsUseCase.getCards(authUseCase.getCurrentUser()!!.id)
                    .map { CardHolderModel(
                        CardStyle.entries[it.style],
                        it.name,
                        it.number,
                        it.dateExpired
                    ) }
            )
            _state.postValue(State.InitScreen(currentList))
        }
    }

    suspend fun getCurrentUser(): UserModel{
        return authUseCase.getCurrentUser()!!
    }

    fun addCard(card: CardModel){
        viewModelScope.launch(Dispatchers.IO) {
            cardsUseCase.createCard(
                card.name,
                card.style,
                card.number
            ).let {
                cardsUseCase.addCardToUser(it.id)
                initScreen()
            }
        }
    }

    sealed class State(){
        class SimpleState(): State()
        class InitScreen(val list: List<CardHolderModel>): State()
    }

}