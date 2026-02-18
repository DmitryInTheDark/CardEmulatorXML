package com.example.cardemulator.di

import android.content.Context
import com.example.cardemulator.MainActivity
import com.example.cardemulator.fragments.auth.AuthFragment
import com.example.cardemulator.fragments.main.cards.CardFragmentContainer
import com.example.cardemulator.fragments.main.cards.CardsFragment
import com.example.cardemulator.fragments.main.cards.pay.PayFragment
import com.example.cardemulator.fragments.main.home.HomeFragment
import com.example.cardemulator.fragments.main.profile.ProfileFragment
import com.example.cardemulator.fragments.registration.ChooseCardStyleBottomSheet
import com.example.cardemulator.fragments.registration.RegistrationFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class, UseCaseModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: AuthFragment)
    fun inject(fragment: ChooseCardStyleBottomSheet)
//    fun inject(fragment: MakeCardFragment)
    fun inject(fragment: PayFragment)
//    fun inject(fragment: CardsFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: CardsFragment)
    fun inject(fragment: CardFragmentContainer)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance context: Context,
            appModule: AppModule
        ): AppComponent
    }

}