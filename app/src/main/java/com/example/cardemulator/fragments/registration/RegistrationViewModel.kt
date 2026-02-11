package com.example.cardemulator.fragments.registration

import com.example.base.base.BaseViewModel
import com.example.base.base.style.CardStyle
import com.example.domain.use_case.auth.AuthUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): BaseViewModel() {

    fun registration(name: String, email: String, number: String, password: String, secretKey: String, cardStyle: CardStyle){
        CoroutineScope(Dispatchers.IO).launch {
            authUseCase.registration(name, email, number, password, secretKey, cardStyle.ordinal)
        }
    }
}