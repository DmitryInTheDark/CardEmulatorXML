package com.example.cardemulator.fragments.main.profile

import androidx.fragment.app.viewModels
import com.example.base.base.BaseFragment
import com.example.cardemulator.app.CardEmulatorApp
import com.example.cardemulator.databinding.FragmentProfileBinding

class ProfileFragment: BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override val viewModel: ProfileViewModel by viewModels()

    override fun initializeBinding() = FragmentProfileBinding.inflate(layoutInflater)

    override fun setupObservers() {
    }

    override fun setupUI() {
    }

    override fun setupListeners() {
    }

    override fun inject() = (requireActivity().application as CardEmulatorApp).appComponent.inject(this)
}