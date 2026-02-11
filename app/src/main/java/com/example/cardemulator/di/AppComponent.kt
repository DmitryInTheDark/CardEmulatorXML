package com.example.cardemulator.di

import android.content.Context
import com.example.cardemulator.fragments.registration.RegistrationFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class, UseCaseModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: RegistrationFragment)
//    fun inject(fragment: MakeCardFragment)
//    fun inject(fragment: PayFragment)
//    fun inject(fragment: CardsFragment)
//    fun inject(fragment: HomeFragment)
//    fun inject(fragment: ProfileFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

}