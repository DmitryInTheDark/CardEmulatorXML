package com.example.cardemulator.fragments.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.base.BaseViewModel
import com.example.base.base.holder.CardHolderModel
import com.example.domain.use_case.auth.AuthUseCase
import com.example.domain.use_case.cards.CardsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): BaseViewModel() {

    private val _state = MutableLiveData<State>(State.SimpleState())
    val state: LiveData<State> = _state

    fun initScreen(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(State.InitScreen(authUseCase.getCurrentUser()!!.name))
        }
    }

    fun clearUser(){
        authUseCase.leaveAccount()
    }

    sealed class State(){
        class SimpleState(): State()
        class InitScreen(val name: String): State()
    }
}