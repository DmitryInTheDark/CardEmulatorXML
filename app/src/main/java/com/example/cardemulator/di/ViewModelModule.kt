package com.example.cardemulator.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardemulator.fragments.auth.AuthViewModel
import com.example.cardemulator.fragments.main.MainViewModel
import com.example.cardemulator.fragments.main.cards.pay.PayViewModel
import com.example.cardemulator.fragments.main.home.HomeViewModel
import com.example.cardemulator.fragments.main.profile.ProfileViewModel
import com.example.cardemulator.fragments.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract interface ViewModelModule{

    @Binds
    abstract fun bindFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(vm: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun bindRegistrationViewModel(vm: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(vm: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(vm: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PayViewModel::class)
    abstract fun bindPayViewModel(vm: PayViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(vm: ProfileViewModel): ViewModel

}