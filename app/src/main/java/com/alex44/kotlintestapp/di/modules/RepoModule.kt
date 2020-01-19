package com.alex44.kotlintestapp.di.modules

import com.alex44.kotlintestapp.common.interfaces.INetworkStatus
import com.alex44.kotlintestapp.model.api.IDataSource
import com.alex44.kotlintestapp.model.repo.DataRepo
import com.alex44.kotlintestapp.model.repo.IDataRepo
import com.alex44.kotlintestapp.model.repo.IRepoCache
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class, NetworkModule::class, CacheModule::class])
class RepoModule {

    @Provides
    fun repo(source : IDataSource, repoCache : IRepoCache, networkStatus: INetworkStatus) : IDataRepo =
        DataRepo(source, repoCache, networkStatus)

}