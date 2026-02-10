package com.example.cardemulator.di

import com.example.data.data.local.repository.UserRepository
import com.example.domain.use_case.auth.AuthUseCase
import com.example.domain.use_case.cards.CardsUseCase
import com.example.domain.use_case.profile.ProfileUseCase
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract interface UseCaseModule {

    @Binds
    @Reusable
    abstract fun bindAuthUseCase(userRepository: UserRepository): AuthUseCase

    @Binds
    @Reusable
    abstract fun bindCardsUseCase(userRepository: UserRepository): CardsUseCase

    @Binds
    @Reusable
    abstract fun bindProfileUseCase(userRepository: UserRepository): ProfileUseCase

}