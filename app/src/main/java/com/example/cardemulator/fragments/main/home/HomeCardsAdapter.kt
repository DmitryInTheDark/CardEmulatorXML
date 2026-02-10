package com.example.cardemulator.fragments.main.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.holder.CardHolderModel
import com.example.cardemulator.databinding.HolderHomeCardBinding

class HomeCardsAdapter: RecyclerView.Adapter<HomeCardViewHolder>() {

    val currentList = mutableListOf<CardHolderModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = HomeCardViewHolder(
        HolderHomeCardBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))

    override fun onBindViewHolder(
        holder: HomeCardViewHolder,
        position: Int
    ) = holder.bind(currentList[position])

    override fun getItemCount() = currentList.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<CardHolderModel>){
        currentList.clear()
        currentList.addAll(list)
        notifyDataSetChanged()
    }
}