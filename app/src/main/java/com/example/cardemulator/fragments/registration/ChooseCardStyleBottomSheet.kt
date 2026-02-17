package com.example.cardemulator.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.base.holder.CardHolderModel
import com.example.base.base.style.CardStyle
import com.example.cardemulator.app.CardEmulatorApp
import com.example.cardemulator.databinding.BottomSheetMakeCardBinding
import com.example.cardemulator.fragments.main.home.HomeCardsAdapter
import com.example.domain.use_case.cards.CardsUseCase
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChooseCardStyleBottomSheet(
    private val name: String,
    private val onDismissCallback: (style: CardStyle, number: String) -> Unit
): BottomSheetDialogFragment(), HomeCardsAdapter.OnClickListener {

    @Inject
    lateinit var cardsUseCase: CardsUseCase

    private val binding by lazy { BottomSheetMakeCardBinding.inflate(layoutInflater) }
    private val stylesList = CardStyle.entries.map {
        CardHolderModel(it, name, "1234 5678 9012 3456", "03/33")
    }
    private var selectedCardStyle = CardStyle.entries.first()
    private val adapter = HomeCardsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    fun inject() = (requireActivity().application as CardEmulatorApp).appComponent.inject(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        setupListeners()
        setupFullHeight()
        with(binding) {
            rvStyles.adapter = adapter
            rvStyles.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            adapter.submitList(stylesList)
        }

        binding.mainCard.apply {
            tvNumber.text = "1234 5678 9012 3456"
            tvName.text = name
            tvDateExpired .text = "03/33"
        }
    }

    private fun setupFullHeight() {
        dialog?.setOnShowListener { dialog ->
            val bottomSheetDialog = dialog as BottomSheetDialog
            val bottomSheetInternal = bottomSheetDialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            ) ?: return@setOnShowListener

            val behavior = BottomSheetBehavior.from(bottomSheetInternal)

            // Расширяем на весь экран
            bottomSheetInternal.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            bottomSheetInternal.requestLayout()

            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isFitToContents = true  // Важный параметр для полного растяжения
            behavior.skipCollapsed = true
        }
    }

    fun setupListeners() = with(binding) {
        bChooseStyle.setOnClickListener {
            lifecycleScope.launch {
                val randomNumber = withContext(Dispatchers.IO) {
                    getRandomCardNumber()
                }
                onDismissCallback.invoke(selectedCardStyle, randomNumber)
                dismiss()
            }
        }
    }

    override fun onCardClick(model: CardHolderModel) {
        selectedCardStyle = model.style
        binding.mainCard.apply {
            llCard.setBackgroundResource(model.style.image)
            tvName.text = model.name
            tvName.setTextColor(requireContext().getColor(model.style.textColor))
            tvNumber.text = model.number
            tvNumber.setTextColor(requireContext().getColor(model.style.textColor))
            tvDateExpired.text = model.dateExpired
            tvDateExpired.setTextColor(requireContext().getColor(model.style.textColor))
        }
    }

    fun getRandomCardNumber(): String {
        val number = generateRandomCardNumber()
        val exists = cardsUseCase.isCardNumberExists(number)
        return if (!exists) number
        else generateRandomCardNumber()
    }

    private fun generateRandomCardNumber(): String {
        val digits = (1..16)
            .map { (0..9).random() }
            .joinToString("")
        return digits.chunked(4).joinToString(" ")
    }
}