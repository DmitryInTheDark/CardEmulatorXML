package com.example.cardemulator.fragments.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.BasePagingFragment
import com.example.base.base.holder.CardHolderModel
import com.example.cardemulator.app.CardEmulatorApp
import com.example.cardemulator.databinding.FragmentHomeBinding
import com.example.cardemulator.fragments.registration.ChooseCardStyleBottomSheet
import com.example.domain.models.CardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment: BasePagingFragment<CardHolderModel, HomeCardViewHolder, HomeViewModel, FragmentHomeBinding>(),
HomeCardsAdapter.OnClickListener{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: HomeViewModel by viewModels<HomeViewModel>{ viewModelFactory }
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
                                        dateExpired = "03/33"
                                    )
                                )
                            }
                        )
                        with(Dispatchers.Main){screen.show(childFragmentManager, ChooseCardStyleBottomSheet::class.simpleName)}
                    }
                }
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