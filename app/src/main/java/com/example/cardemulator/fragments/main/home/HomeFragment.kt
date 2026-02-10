package com.example.cardemulator.fragments.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.BasePagingFragment
import com.example.base.base.holder.CardHolderModel
import com.example.base.base.style.CardStyle
import com.example.cardemulator.databinding.FragmentHomeBinding
import com.example.cardemulator.fragments.main.cards.CardsViewModel

class HomeFragment: BasePagingFragment<CardHolderModel, HomeCardViewHolder, HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by viewModels()
    private val cardsAdapter = HomeCardsAdapter()

    override fun initializeBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun setupObservers() {
    }

    override fun setupUI() {
    }

    override fun setupListeners() {
    }

    override fun setupAdapterAndRecyclerView(): Pair<RecyclerView, RecyclerView.Adapter<HomeCardViewHolder>> {
        with(binding){
            return binding.rvCards to cardsAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        cardsAdapter.submitList(list = listOf(CardHolderModel(
            CardStyle.CAT,
            "John Doe",
            "1234 5678 9012 3456",
            "03/33"
        ),
            CardHolderModel(
                CardStyle.HEAVEN,
                "John Doe",
                "1234 5678 9012 3456",
                "03/33"
            )
            ))
    }
}