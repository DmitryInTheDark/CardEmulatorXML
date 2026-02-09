package com.example.cardemulator.fragments.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardemulator.databinding.HolderHomeCardBinding

class HomeCardsAdapter: RecyclerView.Adapter<HomeCardViewHolder>() {

    val currentList = listOf<HomeCardViewHolder>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = HomeCardViewHolder(
        HolderHomeCardBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))

    override fun onBindViewHolder(
        holder: HomeCardViewHolder,
        position: Int
    ) = holder.bind()

    override fun getItemCount() = currentList.size

}