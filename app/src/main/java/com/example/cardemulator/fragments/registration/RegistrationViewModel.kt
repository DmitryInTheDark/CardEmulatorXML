package com.example.cardemulator.fragments.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.base.BaseViewModel
import com.example.base.base.style.CardStyle
import com.example.domain.use_case.auth.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): BaseViewModel() {

    private val _state = MutableLiveData<State>(State.SimpleState())
    val state: LiveData<State> = _state

    fun registration(name: String, email: String, number: String, password: String, cardStyle: CardStyle){
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.registration(name, email, number, password, cardStyle.ordinal)
            with(Dispatchers.Main){
                _state.postValue(State.SuccessRegistration())
            }
        }
    }

    fun checkLogin(email: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = authUseCase.checkEmails(email)
            _state.postValue(
                if (result) State.CreateCard()
                else State.Error("Такой логин уже существует")
            )
        }
    }

    sealed class State(){
        class SimpleState(): State()
        class SuccessRegistration(): State()
        class Error(val message: String): State()
        class CreateCard(): State()
    }
}