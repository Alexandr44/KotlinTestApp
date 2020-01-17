package com.alex44.kotlintestapp.di

import com.alex44.kotlintestapp.di.modules.AppModule
import com.alex44.kotlintestapp.di.modules.ImageModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ImageModule::class])
interface AppComponent {
}