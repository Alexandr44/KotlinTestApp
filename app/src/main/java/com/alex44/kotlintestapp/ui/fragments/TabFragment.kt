package com.alex44.kotlintestapp.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alex44.kotlintestapp.App
import com.alex44.kotlintestapp.model.enums.TabType
import com.alex44.kotlintestapp.presenters.TabPresenter
import com.alex44.kotlintestapp.views.TabView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_tab.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class TabFragment : MvpAppCompatFragment(), TabView {

    @InjectPresenter
    lateinit var presenter: TabPresenter

    @Inject
    lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        var data = savedInstanceState?.getSerializable("data") as ArrayList<DataDTO>?
        App.instance.appComponent.inject(this)
        // Inflate the layout for this fragment
        return inflater.inflate(com.alex44.kotlintestapp.R.layout.fragment_tab, container, false)
    }

    @ProvidePresenter
    fun createPresenter(): TabPresenter {
        var type = arguments?.getSerializable("type") as TabType
        val tabPresenter = TabPresenter(type)
        App.instance.appComponent.inject(tabPresenter)
        return tabPresenter
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun initRV() {

    }

    override fun updateRV() {
        text_view.text = presenter.data.size.toString()
    }

    override fun goToDetails() {

    }

}
