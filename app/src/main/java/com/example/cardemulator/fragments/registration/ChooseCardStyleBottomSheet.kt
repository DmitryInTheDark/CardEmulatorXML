package com.example.cardemulator.fragments.registration

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.base.holder.CardHolderModel
import com.example.base.base.style.CardStyle
import com.example.cardemulator.databinding.BottomSheetMakeCardBinding
import com.example.cardemulator.fragments.main.home.HomeCardsAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseCardStyleBottomSheet(
    private val name: String,
    private val onDismissCallback: (style: CardStyle, number: String) -> Unit
): BottomSheetDialogFragment(), HomeCardsAdapter.OnClickListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        etCardNumber.addTextChangedListener(object : TextWatcher {
            private var isFormatting = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (isFormatting || s == null) return
                isFormatting = true
                s.let {
                    val digitsOnly = it.toString().replace(" ", "")
                    val formatted = StringBuilder()
                    for (i in digitsOnly.indices) {
                        if (i > 0 && i % 4 == 0) {
                            formatted.append(" ")
                        }
                        formatted.append(digitsOnly[i])
                    }
                    it.replace(0, it.length, formatted.toString())
                }
                isFormatting = false
            }
        })
        tilCardNumber.setEndIconOnClickListener { dismiss() }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissCallback.invoke(
            selectedCardStyle,
            binding.etCardNumber.text.toString()
        )
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
}