package com.alex44.kotlintestapp.di.modules

import com.alex44.kotlintestapp.model.repo.IRepoCache
import com.alex44.kotlintestapp.model.repo.PaperCache
import dagger.Module
import dagger.Provides

@Module
class CacheModule {

    @Provides
    fun paperModule() : IRepoCache = PaperCache()

}