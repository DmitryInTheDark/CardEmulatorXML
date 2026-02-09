package com.example.cardemulator.fragments.auth

import androidx.fragment.app.viewModels
import com.example.base.base.BaseFragment
import com.example.cardemulator.R
import com.example.cardemulator.databinding.FragmentAuthBinding
import com.example.cardemulator.util.FieldValidator

class AuthFragment: BaseFragment<AuthViewModel, FragmentAuthBinding>() {

    override val viewModel: AuthViewModel by viewModels()

    override fun initializeBinding(): FragmentAuthBinding = FragmentAuthBinding.inflate(layoutInflater)

    override fun setupObservers() {

    }

    override fun setupUI() {

    }

    override fun setupListeners() = with(binding) {
        bEnter.setOnClickListener {
            if (FieldValidator.isValidEmail((tilLogin.editText?.text ?: "").toString())) {
                tilLogin.error = null
            } else {
                tilLogin.error = getString(R.string.incorrect_email)
            }
            if (FieldValidator.isValidPassword((tilPassword.editText?.text ?: "").toString())) {
                tilPassword.error = null
            } else {
                tilPassword.error = getString(R.string.incorrect_password)
            }
            navigateTo(AuthFragmentDirections.actionAuthFragmentToMainFragment())
        }
        tvRegistration.setOnClickListener { navigateTo(R.id.registrationFragment) }
    }

}