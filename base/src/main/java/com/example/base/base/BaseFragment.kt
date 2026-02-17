package com.example.base.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(): Fragment() {

    protected abstract val viewModel: VM
    protected val binding: VB
        get() = _binding

    abstract fun initializeBinding(): VB
    abstract fun setupObservers()
    abstract fun setupUI()
    abstract fun setupListeners()
    abstract protected fun inject()

    private lateinit var _binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initializeBinding()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupListeners()
        setupObservers()
    }

    protected fun showToast(@StringRes text: Int){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    protected fun showToast(text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    protected fun navigateTo(fragmentId: Int) = findNavController().navigate(fragmentId)
    protected fun navigateTo(direction: NavDirections) = findNavController().navigate(direction)
    protected fun navigateBack() = findNavController().popBackStack()

}