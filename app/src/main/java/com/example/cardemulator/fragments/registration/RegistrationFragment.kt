package com.example.cardemulator.fragments.registration

import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.base.base.BaseFragment
import com.example.cardemulator.R
import com.example.cardemulator.app.CardEmulatorApp
import com.example.cardemulator.databinding.FragmentRegistrationBinding
import com.example.cardemulator.util.FieldValidator
import javax.inject.Inject

class RegistrationFragment: BaseFragment<RegistrationViewModel, FragmentRegistrationBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: RegistrationViewModel by viewModels<RegistrationViewModel> { viewModelFactory }
    private val fields by lazy { with(binding){
        listOf(tilName, tilEmail, tilPassword, tilRepeatPassword)
        }
    }

    override fun initializeBinding(): FragmentRegistrationBinding = FragmentRegistrationBinding.inflate(layoutInflater)

    override fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is RegistrationViewModel.State.SuccessRegistration -> {
                    navigateTo(RegistrationFragmentDirections.actionRegistrationFragmentToMainFragment())
                }
                is RegistrationViewModel.State.Error -> showToast(it.message)
                is RegistrationViewModel.State.CreateCard -> {
                    with(binding){
                        ChooseCardStyleBottomSheet(name = tilName.editText!!.text.toString()){ style, number ->
                            viewModel.registration(
                                tilName.editText!!.text.toString(),
                                tilEmail.editText!!.text.toString(),
                                number,
                                tilPassword.editText!!.text.toString(),
                                style
                            )
                        }.show(childFragmentManager, ChooseCardStyleBottomSheet::class.simpleName)
                    }
                }

                else -> Unit
            }
        }
    }

    override fun setupUI() {
    }

    override fun inject() = (requireActivity().application as CardEmulatorApp).appComponent.inject(this)

    override fun setupListeners() = with(binding){
        ibBack.setOnClickListener { findNavController().popBackStack() }
        tilName.editText?.doOnTextChanged { text, _, _, _ ->
            text?.let {
                val formatted = it.toString().replaceFirstChar { char ->
                    if (char.isLowerCase()) char.titlecase()
                    else char.toString()
                }

                if (formatted != it.toString()) {
                    tilName.editText?.setText(formatted)
                    tilName.editText?.setSelection(formatted.length)
                }
            }
            if (!FieldValidator.isValidName(text.toString())) tilName.error = getString(R.string.uncorrect_value)
            else tilName.error = null
        }
        tilEmail.editText?.doOnTextChanged { text, _, _, _ ->
            if (!FieldValidator.isValidEmail(text.toString())) tilEmail.error = getString(R.string.uncorrect_value)
            else tilEmail.error = null
        }
        tilPassword.editText?.doOnTextChanged { text, _, _, _ ->
            if (!FieldValidator.isValidPassword(text.toString())) tilPassword.error = getString(R.string.uncorrect_value)
            else tilPassword.error = null
            if (tilRepeatPassword.editText?.text.toString() != text.toString()) tilRepeatPassword.error =  getString(R.string.uncorrect_value)
            else tilRepeatPassword.error = null
        }
        tilRepeatPassword.editText?.doOnTextChanged { text, _, _, _ ->
            if (text.toString() != tilPassword.editText?.text.toString()) tilRepeatPassword.error = getString(R.string.uncorrect_value)
            else tilRepeatPassword.error = null
        }
        bRegistration.setOnClickListener {
            val emptyFields = fields.filter { it.editText?.text?.isEmpty() ?: true }
            if (emptyFields.isNotEmpty()){
                emptyFields.forEach { it.error = getString(R.string.empty_field_error) }
                return@setOnClickListener
            }
            if (fields.any { it.error == null }){
                viewModel.checkLogin(tilEmail.editText?.text.toString())
            }
        }
    }
}