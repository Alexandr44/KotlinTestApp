package com.alex44.kotlintestapp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TabView : MvpView {

    fun showMessage(message: String)

    fun initRV()

    fun updateRV()

}