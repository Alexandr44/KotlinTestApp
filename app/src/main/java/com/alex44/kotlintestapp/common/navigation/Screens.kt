package com.alex44.kotlintestapp.common.navigation

import androidx.fragment.app.Fragment
import com.alex44.kotlintestapp.ui.fragments.DetailFragment
import com.alex44.kotlintestapp.ui.fragments.HomeFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class HomeScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = HomeFragment.newInstance();
    }

    class DetailScreen(private val title : String, private val imgUrl : String) : SupportAppScreen() {
        override fun getFragment(): Fragment = DetailFragment.newInstance(title, imgUrl)
    }

}

