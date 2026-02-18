package com.example.cardemulator.fragments.main.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.base.BaseFragment
import com.example.base.base.holder.CardHolderModelWithId
import com.example.base.base.style.CardStyle
import com.example.cardemulator.R
import com.example.cardemulator.app.CardEmulatorApp
import com.example.cardemulator.databinding.FragmentCardsBinding
import com.example.domain.use_case.auth.AuthUseCase
import com.example.domain.use_case.cards.CardsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardFragmentContainer: Fragment(), CardAdapter.OnClickListener {

    @Inject
    lateinit var cardsUseCase: CardsUseCase

    private val binding by lazy { FragmentCardsBinding.inflate(layoutInflater) }
    private val adapter = CardAdapter(this)

    @Inject
    lateinit var authUseCase: AuthUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as CardEmulatorApp).appComponent.inject(this)
        binding.rvCards.adapter = adapter
        binding.rvCards.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        lifecycleScope.launch(Dispatchers.IO) {
            val cards = cardsUseCase.getCards(authUseCase.getCurrentUser()!!.id)
            withContext(Dispatchers.Main){
                adapter.submitList(cards.map { CardHolderModelWithId(it.id, CardStyle.entries[it.style], it.name, it.number, it.dateExpired) })
            }
        }
    }

    override fun onCardClick(model: CardHolderModelWithId) {
        findNavController().navigate(
            CardFragmentContainerDirections.actionCardFragmentContainerToPayFragment(model.id)
        )
    }
}