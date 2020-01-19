package com.alex44.kotlintestapp.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alex44.kotlintestapp.App
import com.alex44.kotlintestapp.R
import com.alex44.kotlintestapp.common.navigation.Screens
import com.alex44.kotlintestapp.presenters.MainPresenter
import com.alex44.kotlintestapp.views.MainView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.alert
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var navigatorHolder : NavigatorHolder

    private var navigator: Navigator = object : SupportAppNavigator(this, R.id.main_frame_layout) {
        override fun setupFragmentTransaction(command: Command?,
                                              currentFragment: Fragment?,
                                              nextFragment: Fragment?,
                                              fragmentTransaction: FragmentTransaction?) {
            super.setupFragmentTransaction(
                command,
                currentFragment,
                nextFragment,
                fragmentTransaction
            )
            fragmentTransaction?.setCustomAnimations(R.anim.slide_in, R.anim.slide_out,
                R.anim.slide_in, R.anim.slide_out)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    @ProvidePresenter
    fun createPresenter() : MainPresenter {
        val presenter = MainPresenter(AndroidSchedulers.mainThread())
        App.instance.appComponent.inject(presenter)
        return presenter
    }

    override fun onBackPressed() {
        alert {
            title = "Выход"
            message = "Вы действительно хотите выйти?"
            positiveButton("Да") {router.exit()}
            negativeButton("Нет") {it.dismiss()}

        }.show()
    }

    override fun goToHomeScreen() {
        router.newRootScreen(Screens.HomeScreen())
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
