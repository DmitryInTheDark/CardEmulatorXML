package com.example.cardemulator.fragments.restore_password

import androidx.fragment.app.viewModels
import com.example.base.base.BaseFragment
import com.example.cardemulator.databinding.FragmentRestorePasswordBinding

class RestorePasswordFragment: BaseFragment<RestorePasswordViewModel, FragmentRestorePasswordBinding>() {

    override val viewModel: RestorePasswordViewModel by viewModels()

    override fun initializeBinding(): FragmentRestorePasswordBinding = FragmentRestorePasswordBinding.inflate(layoutInflater)

    override fun setupObservers() {
    }

    override fun setupUI() {
    }

    override fun setupListeners() = with(binding){

    }
}