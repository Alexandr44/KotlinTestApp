package com.alex44.kotlintestapp.ui.fragments


import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex44.kotlintestapp.App
import com.alex44.kotlintestapp.model.enums.TabType
import com.alex44.kotlintestapp.presenters.TabPresenter
import com.alex44.kotlintestapp.ui.adapters.DataRvAdapter
import com.alex44.kotlintestapp.views.TabView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_tab.*

class TabFragment : MvpAppCompatFragment(), TabView {

    @InjectPresenter
    lateinit var presenter: TabPresenter

    private var adapter : DataRvAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        adapter = DataRvAdapter(presenter)
        App.instance.appComponent.inject(adapter as DataRvAdapter)
        data_rv.layoutManager = LinearLayoutManager(context)
        data_rv.adapter = adapter
    }

    override fun updateRV() {
        adapter?.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        saveValues(presenter.type.ordinal)
    }

    override fun onResume() {
        super.onResume()
        restoreValues(presenter.type.ordinal)
    }

    private fun restoreValues(type : Int) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val currentVisiblePosition = preferences?.getInt("rvPosition$type", 0)?:0
        (data_rv?.layoutManager as? LinearLayoutManager)?.scrollToPosition(currentVisiblePosition)
        timber.log.Timber.d("test")
    }

    private fun saveValues(type : Int) {
        val currentVisiblePosition = (data_rv?.layoutManager as? LinearLayoutManager)?.findFirstCompletelyVisibleItemPosition()
        if (currentVisiblePosition != null) {
            if (currentVisiblePosition < 0) return
        }
        PreferenceManager.getDefaultSharedPreferences(context)
            ?.edit()
            ?.putInt("rvPosition$type", currentVisiblePosition?:0)
            ?.apply()
    }

}
