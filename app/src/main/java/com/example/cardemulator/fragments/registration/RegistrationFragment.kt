package com.example.cardemulator.fragments.registration

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.base.base.BaseFragment
import com.example.cardemulator.databinding.FragmentRegistrationBinding

class RegistrationFragment: BaseFragment<RegistrationViewModel, FragmentRegistrationBinding>() {

    override val viewModel: RegistrationViewModel by viewModels()

    override fun initializeBinding(): FragmentRegistrationBinding = FragmentRegistrationBinding.inflate(layoutInflater)

    override fun setupObservers() {
    }

    override fun setupUI() {
    }

    override fun setupListeners() = with(binding){
        ibBack.setOnClickListener { findNavController().popBackStack() }
    }
}