package com.alex44.kotlintestapp.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alex44.kotlintestapp.R
import com.alex44.kotlintestapp.model.enums.TabType
import com.alex44.kotlintestapp.presenters.HomePresenter
import com.alex44.kotlintestapp.views.HomeView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : MvpAppCompatFragment(), HomeView {

    @InjectPresenter
    lateinit var presenter: HomePresenter

    var tabPosition : Int = 0

    val fragments : MutableList<Fragment> = ArrayList()

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

    override fun init() {
        initFragments()
        initTabs()
    }

    private fun initTabs() {
        home_tab_layout.addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {selectedTab ->
                    if (selectedTab.position < fragments.size) {
                        replaceTabFragment(fragments[selectedTab.position], selectedTab.position > tabPosition)
                        tabPosition = selectedTab.position
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
        home_tab_layout.addTab(home_tab_layout.newTab().setText("Cats"))
        home_tab_layout.addTab(home_tab_layout.newTab().setText("Dogs"))
    }

    private fun initFragments() {
        fragments.add(createFragment(TabType.CATS))
        fragments.add(createFragment(TabType.DOGS))
    }

    fun replaceTabFragment(fragment: Fragment, directionForward : Boolean) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.setCustomAnimations(if (directionForward) R.anim.slide_in else R.anim.slide_in_back,
                if (directionForward) R.anim.slide_out else R.anim.slide_out_back)
            ?.replace(R.id.home_frame_layout, fragment)
            ?.commit()
    }

    private fun createFragment(tabType: TabType) : TabFragment {
        val fragment = TabFragment()
        val bundle = Bundle()
        bundle.putSerializable("type", tabType)
        fragment.arguments = bundle
        return fragment
    }

}
