package com.example.cardemulator.fragments.auth

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.base.base.BaseFragment
import com.example.cardemulator.R
import com.example.cardemulator.app.CardEmulatorApp
import com.example.cardemulator.databinding.FragmentAuthBinding
import com.example.cardemulator.util.FieldValidator
import javax.inject.Inject

class AuthFragment: BaseFragment<AuthViewModel, FragmentAuthBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: AuthViewModel by viewModels<AuthViewModel>{viewModelFactory}
    private val fields by lazy { with(binding){
        listOf(tilLogin, tilPassword)
        }
    }

    override fun initializeBinding(): FragmentAuthBinding = FragmentAuthBinding.inflate(layoutInflater)

    override fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is AuthViewModel.State.ErrorAuth -> showToast("Неверный логин или пароль")
                is AuthViewModel.State.SuccessAuth -> navigateTo(AuthFragmentDirections.actionAuthFragmentToMainFragment())
                else -> Unit
            }
        }
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
            if (fields.all { it.error == null }){
                viewModel.auth(tilLogin.editText?.text.toString(), tilPassword.editText?.text.toString())
            }
        }
        tvRegistration.setOnClickListener { navigateTo(R.id.registrationFragment) }
    }

    override fun inject() = (requireActivity().application as CardEmulatorApp).appComponent.inject(this)

}