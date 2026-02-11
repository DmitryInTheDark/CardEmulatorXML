package com.example.cardemulator.fragments.main.cards

import androidx.fragment.app.viewModels
import com.example.base.base.BaseFragment
import com.example.cardemulator.databinding.FragmentCardsBinding

class CardsFragment: BaseFragment<CardsViewModel, FragmentCardsBinding>() {

    override val viewModel: CardsViewModel by viewModels()

    override fun initializeBinding() = FragmentCardsBinding.inflate(layoutInflater)

    override fun setupObservers() {
    }

    override fun setupUI() {
    }

    override fun setupListeners() {

    }

    override fun inject() {

    }
}