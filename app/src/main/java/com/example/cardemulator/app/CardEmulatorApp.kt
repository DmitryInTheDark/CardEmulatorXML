package com.example.cardemulator.app

import android.app.Application
import com.example.cardemulator.di.AppComponent
import com.example.cardemulator.di.DaggerAppComponent

class CardEmulatorApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()

    }

}