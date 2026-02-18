package com.example.cardemulator.fragments.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.example.base.base.BaseFragment
import com.example.cardemulator.MainActivity
import com.example.cardemulator.R
import com.example.cardemulator.app.CardEmulatorApp
import com.example.cardemulator.databinding.FragmentProfileBinding
import com.example.cardemulator.fragments.main.MainFragment
import com.example.domain.models.UserModel
import javax.inject.Inject

class ProfileFragment: BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: ProfileViewModel by viewModels<ProfileViewModel>{ viewModelFactory }

    override fun initializeBinding() = FragmentProfileBinding.inflate(layoutInflater)

    override fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is ProfileViewModel.State.InitScreen -> setupUi(it.name)
                else -> Unit
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initScreen()
    }

    fun setupUi(name: String){
        binding.tvName.text = name
    }

    override fun setupUI() = Unit

    override fun setupListeners() {
        binding.tvLeaveAccount.setOnClickListener {
            viewModel.clearUser()
            (requireActivity() as MainActivity).navBack()
        }
    }

    override fun inject() = (requireActivity().application as CardEmulatorApp).appComponent.inject(this)
}