package com.alex44.kotlintestapp.presenters

import com.alex44.kotlintestapp.common.navigation.Screens
import com.alex44.kotlintestapp.views.DetailView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class DetailPresenter(private val title : String, private val imgUrl : String) : MvpPresenter<DetailView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        init()
    }

    private fun init() {
        viewState.setTitle(title)
        viewState.setImage(imgUrl)
    }

    fun backClicked() : Boolean {
        router.newRootScreen(Screens.HomeScreen())
        return true
    }
}