package com.example.cardemulator.fragments.registration

import com.example.base.base.BaseViewModel
import com.example.domain.use_case.auth.AuthUseCase
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): BaseViewModel() {

    fun registration(name: String, email: String, password: String, secretKey: String){
        authUseCase.registration(name, email, password, secretKey, )
    }
}