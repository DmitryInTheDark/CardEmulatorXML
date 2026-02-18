package com.example.cardemulator.fragments.main.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.BasePagingFragment
import com.example.base.base.holder.CardHolderModel
import com.example.cardemulator.app.CardEmulatorApp
import com.example.cardemulator.databinding.DialogBalanceBinding
import com.example.cardemulator.databinding.FragmentHomeBinding
import com.example.cardemulator.fragments.main.cards.CardsFragment
import com.example.cardemulator.fragments.registration.ChooseCardStyleBottomSheet
import com.example.domain.models.CardModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.max

class HomeFragment: BasePagingFragment<CardHolderModel, HomeCardViewHolder, HomeViewModel, FragmentHomeBinding>(),
HomeCardsAdapter.OnClickListener{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: HomeViewModel by viewModels<HomeViewModel>{ viewModelFactory }
    private var maxAmount = 0
    private var amount = 0
    private fun showBalanceDialog(fragment: CardsFragment, cardId: Int) {
        val dialogBinding = DialogBalanceBinding.inflate(layoutInflater)
        lifecycleScope.launch {
            maxAmount = viewModel.getMaxAmount(cardId)
            amount = viewModel.getAmount(cardId)
            withContext(Dispatchers.Main){
                dialogBinding.tvBalance.text = "Текущий баланс: $amount"
            }
        }

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(dialogBinding.root)
            .create()

        dialogBinding.bRegistration.setOnClickListener {
            val value = dialogBinding.tilPassword.editText?.text?.toString()

            if (value.isNullOrEmpty()) {
                dialogBinding.tilPassword.error = "Некорректное значение"
                return@setOnClickListener
            }

            val amount = value.toIntOrNull()
            if (amount == null) {
                dialogBinding.tilPassword.error = "Некорректное значение"
                return@setOnClickListener
            }

            if (amount > maxAmount){
                dialogBinding.tilPassword.error = "Баланс не может превышать 10000"
            }else{
                viewModel.updateSum(cardId, amount)
                dialog.dismiss()
                closeFragment(fragment)
            }

        }
        dialog.show()
    }
    private var currentCardId: Int? = null
    private val cardsAdapter = HomeCardsAdapter(this)

    override fun initializeBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is HomeViewModel.State.InitScreen -> {
                    cardsAdapter.submitList(it.list)
                }
                else -> Unit
            }
        }
    }

    fun closeFragment(fragment: CardsFragment){
        fragment.dismiss()
    }

    override fun setupUI() {
        viewModel.initScreen()
    }

    override fun setupListeners() {
        with(binding){
            llMakeCard.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    with(viewModel.getCurrentUser()){
                        val screen = ChooseCardStyleBottomSheet(
                            name = name,
                            onDismissCallback = { style, number ->
                                viewModel.addCard(
                                    CardModel(
                                        id = 0,
                                        style = style.ordinal,
                                        number = number,
                                        name = name,
                                        dateExpired = "03/33",
                                        amount = 0
                                    )
                                )
                            }
                        )
                        with(Dispatchers.Main){screen.show(childFragmentManager, ChooseCardStyleBottomSheet::class.simpleName)}
                    }
                }
            }
            balance.setOnClickListener {
                CardsFragment{fragment, cardId ->
                    currentCardId = cardId
                    showBalanceDialog(fragment, cardId)
                }.show(childFragmentManager, CardsFragment::class.simpleName)
            }
        }
    }

    override fun inject() = (requireActivity().application as CardEmulatorApp).appComponent.inject(this)

    override fun setupAdapterAndRecyclerView(): Pair<RecyclerView, RecyclerView.Adapter<HomeCardViewHolder>> {
        with(binding){
            return binding.rvCards to cardsAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onCardClick(model: CardHolderModel) = Unit
}