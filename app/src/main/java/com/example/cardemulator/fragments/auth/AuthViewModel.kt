package com.example.cardemulator.fragments.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.base.BaseViewModel
import com.example.domain.use_case.auth.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): BaseViewModel(){

    private val _state = MutableLiveData<State>(State.SimpleState())
    val state: LiveData<State> = _state

    fun auth(login: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = authUseCase.auth(login, password)
            _state.postValue(
                if (response == null) State.ErrorAuth()
                else State.SuccessAuth()
            )
        }
    }

    sealed class State(){
        class SimpleState(): State()
        class SuccessAuth(): State()
        class ErrorAuth(): State()
    }

}