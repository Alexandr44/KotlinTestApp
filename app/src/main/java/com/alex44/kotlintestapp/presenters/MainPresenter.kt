package com.alex44.kotlintestapp.presenters

import com.alex44.kotlintestapp.model.dtos.DataDTO
import com.alex44.kotlintestapp.model.repo.IDataRepo
import com.alex44.kotlintestapp.views.MainView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

@InjectViewState
class MainPresenter(private val mainThreadScheduler : Scheduler) : MvpPresenter<MainView>() {

    @Inject
    lateinit var repo : IDataRepo

    var disposable : Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        prepareDataFromApi()
    }

    private fun prepareDataFromApi() {
        disposable = Maybe.zip(
            repo.loadData("cat"),
            repo.loadData("dog"),
            BiFunction { t1: List<DataDTO>, t2: List<DataDTO> ->
                val errors = ArrayList<String>()
                if (t1.isEmpty()) {
                    errors.add("Can't load Cats")
                }
                if (t2.isEmpty()) {
                    errors.add("Can't load Dogs")
                }
                return@BiFunction errors
            }
        )
            .observeOn(mainThreadScheduler)
            .subscribe({ errors ->
                for (message in errors) {
                    viewState.showMessage(message)
                }
                goToHomeScreen()
            }, { t ->
                t.message?.let { viewState.showMessage(it) }
            })
    }

    private fun goToHomeScreen() {
        viewState.goToHomeScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.let {
            if (!it.isDisposed) it.dispose()
        }
    }

}