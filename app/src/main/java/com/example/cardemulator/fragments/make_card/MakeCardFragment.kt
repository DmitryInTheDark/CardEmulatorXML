package com.example.cardemulator.fragments.make_card

import androidx.fragment.app.viewModels
import com.example.base.base.BaseFragment
import com.example.cardemulator.databinding.FragmentMakeCardBinding

class MakeCardFragment: BaseFragment<MakeCardViewModel, FragmentMakeCardBinding>() {

    override val viewModel: MakeCardViewModel by viewModels()

    override fun initializeBinding() = FragmentMakeCardBinding.inflate(layoutInflater)

override fun inject(){}
    override fun setupObservers() {
    }

    override fun setupUI() {
    }

    override fun setupListeners() {
    }
}