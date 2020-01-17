package com.alex44.kotlintestapp.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alex44.kotlintestapp.R
import com.alex44.kotlintestapp.presenters.HomePresenter
import com.alex44.kotlintestapp.views.HomeView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : MvpAppCompatFragment(), HomeView {

    @InjectPresenter
    lateinit var presenter: HomePresenter

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @ProvidePresenter
    fun createPresenter() = HomePresenter()

    override fun initTabs() {
        home_tab_layout.addTab(home_tab_layout.newTab().setText("Cats"))
        home_tab_layout.addTab(home_tab_layout.newTab().setText("Dogs"))
    }

}
