package com.example.cardemulator.fragments.main.home

import android.R.attr.textColor
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.BaseViewHolder
import com.example.base.base.holder.CardHolderModel
import com.example.cardemulator.databinding.HolderHomeCardBinding

class HomeCardViewHolder(
    private val binding: HolderHomeCardBinding
): BaseViewHolder(binding) {

    fun bind(item: CardHolderModel) = with(binding){
        tvName.apply {
            text = item.name
            setTextColor(context.getColor(item.style.textColor))
        }
        tvNumber.apply {
            text = item.number
            setTextColor(context.getColor(item.style.textColor))
        }
        tvDateExpired.apply {
            text = item.dateExpired
            setTextColor(context.getColor(item.style.textColor))
        }
        llCard.setBackgroundResource(item.style.image)
    }

}