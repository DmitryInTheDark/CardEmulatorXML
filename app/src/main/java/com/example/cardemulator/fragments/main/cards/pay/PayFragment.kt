package com.example.cardemulator.fragments.main.cards.pay

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.example.base.base.BaseFragment
import com.example.base.base.style.CardStyle
import com.example.cardemulator.app.CardEmulatorApp
import com.example.cardemulator.databinding.FragmentPayBinding
import com.example.domain.models.CardModel
import javax.inject.Inject

class PayFragment: BaseFragment<PayViewModel, FragmentPayBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: PayViewModel by viewModels<PayViewModel>{ viewModelFactory }
    var currentCard: CardModel? = null

    override fun initializeBinding() = FragmentPayBinding.inflate(layoutInflater)

    override fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is PayViewModel.State.InitView -> {
                    currentCard = it.card
                    setupUI()
                }
                is PayViewModel.State.SuccessfulPayment -> {
                    updateBalance(it.amount)
                }
                is PayViewModel.State.PaymentError -> {
                    setupError(it.error)
                }
                else -> Unit
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setupUi(requireArguments().getInt("cardId"))
    }

    fun updateBalance(amount: Int){
        binding.tvBalance.text = "Баланс: $amount"
    }

    fun setupError(error: String){
        binding.tvBalance.text = error
    }

    override fun setupUI() {
        if (currentCard == null) return
        val style = CardStyle.entries[currentCard!!.style]
        with(currentCard!!){
            binding.tvBalance.text = "Баланс: $amount"
            binding.card.apply {
                tvName.apply {
                    text = currentCard!!.name
                    setTextColor(context.getColor(style.textColor))
                }
                tvNumber.apply {
                    text = currentCard!!.number
                    setTextColor(context.getColor(style.textColor))
                }
                tvDateExpired.apply {
                    text = currentCard!!.dateExpired
                    setTextColor(context.getColor(style.textColor))
                }
                llCard.setBackgroundResource(style.image)
            }
        }
    }

    override fun setupListeners() {
        with(binding){
            ibBack.setOnClickListener { findNavController().navigateUp() }
            bPay.setOnClickListener { viewModel.pay(currentCard!!.id, 36) }
        }
    }

    override fun inject() = (requireActivity().application as CardEmulatorApp).appComponent.inject(this)
}