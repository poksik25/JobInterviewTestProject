package com.poklad.jobinterviewtestproject

import android.app.Application
import com.poklad.jobinterviewtestproject.di.components.AppComponent
import com.poklad.jobinterviewtestproject.di.components.DaggerAppComponent

class GiphyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        daggerAppComponent = DaggerAppComponent.factory().create(applicationContext)
    }

    companion object {
        lateinit var daggerAppComponent: AppComponent
    }
}