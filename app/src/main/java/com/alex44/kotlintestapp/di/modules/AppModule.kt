package com.alex44.kotlintestapp.di.modules

import com.alex44.kotlintestapp.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app : App) {

    @Provides
    fun app() = app

}