package com.alex44.kotlintestapp

import android.app.Application
import com.alex44.kotlintestapp.di.AppComponent
import com.alex44.kotlintestapp.di.DaggerAppComponent
import com.alex44.kotlintestapp.di.modules.AppModule
import timber.log.Timber

class App : Application() {

    companion object{
        lateinit var instance : App
    }

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}