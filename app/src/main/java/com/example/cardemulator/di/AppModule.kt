package com.example.cardemulator.di

import android.content.Context
import com.example.cardemulator.MainActivity
import com.example.domain.use_case.auth.AuthUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule(context: Context) {

    @Provides
    fun provideMainActivity(authUseCase: AuthUseCase) = MainActivity()
}