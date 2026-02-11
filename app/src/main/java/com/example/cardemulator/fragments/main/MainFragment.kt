package com.example.cardemulator.fragments.main

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnLayout
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.base.base.BaseFragment
import com.example.cardemulator.R
import com.example.cardemulator.databinding.FragmentMainBinding

class MainFragment: BaseFragment<MainViewModel, FragmentMainBinding>() {

    override val viewModel: MainViewModel by viewModels()
    private val navController: NavController by lazy {  childFragmentManager.findFragmentById(R.id.child_nav_host_fragment)!!.findNavController() }

    override fun initializeBinding() = FragmentMainBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeBottomNavigation()
    }

    override fun setupObservers() {

    }

    override fun setupUI() {
    }

    override fun setupListeners() {

    }

    override fun inject(){}

    private fun initializeBottomNavigation(){
        with(binding){
            binding.root.doOnLayout {
                boottomBar.setupWithNavController(navController)
            }
        }
    }
}