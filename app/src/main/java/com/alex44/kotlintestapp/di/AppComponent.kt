package com.alex44.kotlintestapp.di

import com.alex44.kotlintestapp.di.modules.AppModule
import com.alex44.kotlintestapp.di.modules.CiceroneModule
import com.alex44.kotlintestapp.di.modules.RepoModule
import com.alex44.kotlintestapp.di.modules.ImageModule
import com.alex44.kotlintestapp.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ImageModule::class, CiceroneModule::class, RepoModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}