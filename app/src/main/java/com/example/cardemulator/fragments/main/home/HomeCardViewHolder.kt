package com.example.cardemulator.fragments.main.home

import com.example.base.base.BaseViewHolder
import com.example.base.base.holder.CardHolderModel
import com.example.base.base.holder.CardHolderModelWithId
import com.example.cardemulator.databinding.HolderHomeCardBinding
import com.example.cardemulator.fragments.main.cards.CardAdapter

class HomeCardViewHolder(
    private val binding: HolderHomeCardBinding
): BaseViewHolder(binding) {

    fun bind(item: CardHolderModel, callback: HomeCardsAdapter.OnClickListener) = with(binding){
        root.setOnClickListener { callback.onCardClick(item) }
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

    fun bind(item: CardHolderModelWithId, callback: CardAdapter.OnClickListener) = with(binding){
        root.setOnClickListener { callback.onCardClick(item) }
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