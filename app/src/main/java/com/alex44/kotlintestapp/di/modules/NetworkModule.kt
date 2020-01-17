package com.alex44.kotlintestapp.di.modules

import com.alex44.kotlintestapp.App
import com.alex44.kotlintestapp.common.interfaces.INetworkStatus
import com.alex44.kotlintestapp.common.ui.NetworkStatus
import dagger.Module
import dagger.Provides

@Module(includes = [AppModule::class])
class NetworkModule {

    @Provides
    fun networkStatus(app : App) : INetworkStatus = NetworkStatus(app)

}