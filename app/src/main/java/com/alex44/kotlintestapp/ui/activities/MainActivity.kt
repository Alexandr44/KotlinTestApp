package com.alex44.kotlintestapp.ui.activities

import android.os.Bundle
import com.alex44.kotlintestapp.R
import com.alex44.kotlintestapp.presenters.MainPresenter
import com.alex44.kotlintestapp.views.MainView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.jetbrains.anko.alert

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @ProvidePresenter
    fun createPresenter() : MainPresenter = MainPresenter()

    override fun onBackPressed() {
        alert {
            title = "Выход"
            message = "Вы действительно хотите выйти?"
            positiveButton("Да") {super.onBackPressed()}
            negativeButton("Нет") {it.dismiss()}

        }.show()
    }
}
