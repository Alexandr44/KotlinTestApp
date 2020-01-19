package com.alex44.kotlintestapp.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alex44.kotlintestapp.App
import com.alex44.kotlintestapp.R
import com.alex44.kotlintestapp.common.interfaces.BackButtonListener
import com.alex44.kotlintestapp.common.interfaces.IImageLoader
import com.alex44.kotlintestapp.presenters.DetailPresenter
import com.alex44.kotlintestapp.views.DetailView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject
import javax.inject.Named

class DetailFragment : MvpAppCompatFragment(), DetailView, BackButtonListener {

    @InjectPresenter
    lateinit var presenter: DetailPresenter

    @field: [Inject Named("Picasso")]
    lateinit var imageLoader : IImageLoader<ImageView>

    companion object {
        fun newInstance(title : String, imgUrl : String) : DetailFragment {
            val arguments = Bundle()
            arguments.putString("title", title)
            arguments.putString("imgUrl", imgUrl)
            val fragment = DetailFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.instance.appComponent.inject(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    @ProvidePresenter
    fun createPresenter() : DetailPresenter {
        val title = arguments?.getString("title")
        val imgUrl = arguments?.getString("imgUrl")
        val presenter = DetailPresenter(title.orEmpty(), imgUrl.orEmpty())
        App.instance.appComponent.inject(presenter)
        return presenter
    }

    override fun setImage(url: String) {
        imageLoader.loadInto(url, detail_image, 20)
    }

    override fun setTitle(title: String) {
        detail_text.text = title
    }

    override fun backClick(): Boolean {
        return presenter.backClicked()
    }

}
