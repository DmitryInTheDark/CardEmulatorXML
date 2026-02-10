package com.example.cardemulator.fragments.main.cards.pay

import androidx.fragment.app.viewModels
import com.example.base.base.BaseFragment
import com.example.cardemulator.databinding.FragmentPayBinding

class PayFragment: BaseFragment<PayViewModel, FragmentPayBinding>() {

    override val viewModel: PayViewModel by viewModels()

    override fun initializeBinding() = FragmentPayBinding.inflate(layoutInflater)

    override fun setupObservers() {
    }

    override fun setupUI() {
    }

    override fun setupListeners() {
    }
}