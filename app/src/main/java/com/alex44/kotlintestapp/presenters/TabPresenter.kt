package com.alex44.kotlintestapp.presenters

import com.alex44.kotlintestapp.model.dtos.DataDTO
import com.alex44.kotlintestapp.model.enums.TabType
import com.alex44.kotlintestapp.model.repo.IDataRepo
import com.alex44.kotlintestapp.views.TabView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class TabPresenter(val type : TabType) : MvpPresenter<TabView>() {

    @Inject
    lateinit var dataRepo : IDataRepo

    var data = ArrayList<DataDTO>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        init()
        getData()
    }

    private fun init() {
        viewState.initRV()
    }

    private fun getData() {
        val query: String = when (type) {
            TabType.CATS -> "cat"
            TabType.DOGS -> "dog"
        }
        data.addAll(dataRepo.getData(query))
        viewState.updateRV()
    }

}