package com.alex44.kotlintestapp.presenters

import com.alex44.kotlintestapp.common.navigation.Screens
import com.alex44.kotlintestapp.model.dtos.DataDTO
import com.alex44.kotlintestapp.model.enums.TabType
import com.alex44.kotlintestapp.model.repo.IDataRepo
import com.alex44.kotlintestapp.ui.adapters.DataRvAdapter
import com.alex44.kotlintestapp.views.TabView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class TabPresenter(val type : TabType) : MvpPresenter<TabView>() {

    @Inject
    lateinit var dataRepo : IDataRepo

    @Inject
    lateinit var router: Router

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

    fun bind(holder: DataRvAdapter.DataHolder) {
        val dto = data[holder.itemPosition]
        holder.setTitle(dto.title.orEmpty())
        holder.setImage(dto.imgUrl.orEmpty())
    }

    fun clicked(position: Int) {
        val dto = data[position]
        if (dto.title.isNullOrBlank() || dto.imgUrl.isNullOrBlank()) {
            viewState.showMessage("Incorrect data")
        }
        else {
            router.newRootScreen(Screens.DetailScreen(dto.title, dto.imgUrl))
        }
    }

}