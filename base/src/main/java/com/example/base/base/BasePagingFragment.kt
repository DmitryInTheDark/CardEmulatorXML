package com.example.base.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.base.base.interfaces.BaseHolderModel

abstract class BasePagingFragment<H : BaseHolderModel, VH : BaseViewHolder, VM : BasePagingViewModel<H>, VB : ViewBinding>: BaseFragment<VM, VB>() {

    abstract override val viewModel: VM
    protected val recyclerView: RecyclerView
        get() = _recyclerView
        private lateinit var _recyclerView: RecyclerView
    protected val adapter: RecyclerView.Adapter<VH>
        get() = _adapter
        private lateinit var _adapter: RecyclerView.Adapter<VH>

    abstract override fun initializeBinding(): VB

    abstract override fun setupObservers()

    abstract override fun setupUI()

    abstract override fun setupListeners()

    abstract fun setupAdapterAndRecyclerView(): Pair<RecyclerView, RecyclerView.Adapter<VH>>

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapterAndRecyclerView().apply {
            _recyclerView = first
            _adapter = second
            _recyclerView.adapter = _adapter
        }
    }
}