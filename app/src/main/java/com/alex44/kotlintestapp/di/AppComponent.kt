package com.alex44.kotlintestapp.di

import com.alex44.kotlintestapp.di.modules.AppModule
import com.alex44.kotlintestapp.di.modules.CiceroneModule
import com.alex44.kotlintestapp.di.modules.ImageModule
import com.alex44.kotlintestapp.di.modules.RepoModule
import com.alex44.kotlintestapp.presenters.DetailPresenter
import com.alex44.kotlintestapp.presenters.MainPresenter
import com.alex44.kotlintestapp.presenters.TabPresenter
import com.alex44.kotlintestapp.ui.activities.MainActivity
import com.alex44.kotlintestapp.ui.adapters.DataRvAdapter
import com.alex44.kotlintestapp.ui.fragments.DetailFragment
import com.alex44.kotlintestapp.ui.fragments.TabFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ImageModule::class, CiceroneModule::class, RepoModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(tabFragment: TabFragment)
    fun inject(tabPresenter: TabPresenter)
    fun inject(it: MainPresenter): MainPresenter
    fun inject(dataRvAdapter: DataRvAdapter)
    fun inject(detailFragment: DetailFragment)
    fun inject(presenter: DetailPresenter)
}