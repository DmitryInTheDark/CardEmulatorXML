package com.example.cardemulator.fragments.registration

import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.base.BaseFragment
import com.example.cardemulator.R
import com.example.cardemulator.databinding.FragmentRegistrationBinding
import com.example.cardemulator.util.FieldValidator

class RegistrationFragment: BaseFragment<RegistrationViewModel, FragmentRegistrationBinding>() {

    override val viewModel: RegistrationViewModel by viewModels()
    private val fields = with(binding){
        listOf(tilName, tilEmail, tilPassword, tilRepeatPassword, tilSecretKey)
    }

    override fun initializeBinding(): FragmentRegistrationBinding = FragmentRegistrationBinding.inflate(layoutInflater)

    override fun setupObservers() {
    }

    override fun setupUI() {
    }

    override fun setupListeners() = with(binding){
        ibBack.setOnClickListener { findNavController().popBackStack() }
        tilName.editText?.doOnTextChanged { text, _, _, _ ->
            if (!FieldValidator.isValidName(text.toString())) tilName.error = getString(R.string.uncorrect_value)
            else tilName.error = null
        }
        tilEmail.editText?.doOnTextChanged { text, _, _, _ ->
            if (!FieldValidator.isValidEmail(text.toString())) tilEmail.error = getString(R.string.uncorrect_value)
            else tilEmail.error = null
        }
        tilSecretKey.editText?.doOnTextChanged { text, _, _, _ ->
            if ((text?.length ?: 0) < 8) tilSecretKey.error = getString(R.string.uncorrect_value)
            else tilSecretKey.error = null
        }
        tilPassword.editText?.doOnTextChanged { text, _, _, _ ->
            if (!FieldValidator.isValidPassword(text.toString())) tilPassword.error = getString(R.string.uncorrect_value)
            else tilPassword.error = null
        }
        tilRepeatPassword.editText?.doOnTextChanged { text, _, _, _ ->
            if (text.toString() != tilPassword.editText?.text.toString()) tilRepeatPassword.error = getString(R.string.uncorrect_value)
            else tilRepeatPassword.error = null
        }
        bRegistration.setOnClickListener {
            if (fields.all { it.error == null && it.editText?.text != null}){
                ChooseCardStyleBottomSheet(){
                    viewModel.registration(
                        tilName.editText!!.text.toString(),
                        tilEmail.editText!!.text.toString(),
                        tilPassword.editText!!.text.toString(),
                        tilSecretKey.editText!!.text.toString()
                    )
                }.show(childFragmentManager, ChooseCardStyleBottomSheet::class.simpleName)
            }
        }
    }
}