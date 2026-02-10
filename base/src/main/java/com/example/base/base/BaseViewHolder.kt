package com.example.base.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {
    protected val context = binding.root.context
}