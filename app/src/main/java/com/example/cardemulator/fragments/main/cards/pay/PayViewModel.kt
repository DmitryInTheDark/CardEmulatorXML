package com.example.cardemulator.fragments.main.cards.pay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.base.BaseViewModel
import com.example.domain.models.CardModel
import com.example.domain.use_case.cards.CardsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PayViewModel @Inject constructor(
    private val cardsUseCase: CardsUseCase,
    private val authUseCase: CardsUseCase
): BaseViewModel() {

    private val _state = MutableLiveData<State>(
        State.SimpleState()
    )
    val state = _state

    fun pay(cardId: Int, amount: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val newAmount  = cardsUseCase.getCard(cardId).amount - amount
            if(newAmount >= 0){
                cardsUseCase.pay(cardId = cardId, amount = amount)
                _state.postValue(State.SuccessfulPayment(newAmount))
            }else _state.postValue(State.PaymentError("Недостаточно средств"))
        }
    }

    fun setupUi(cardId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(State.InitView(cardsUseCase.getCard(cardId)))
        }
    }

    sealed class State(){

        class InitView(val card: CardModel): State()
        class SimpleState(): State()
        class SuccessfulPayment(val amount: Int): State()
        class PaymentError(val error: String): State()

    }
}