package com.example.cardemulator.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class, UseCaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

}