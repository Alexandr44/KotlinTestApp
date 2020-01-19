package com.alex44.kotlintestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alex44.kotlintestapp.R
import com.alex44.kotlintestapp.common.interfaces.IImageLoader
import com.alex44.kotlintestapp.presenters.TabPresenter
import com.alex44.kotlintestapp.views.TabRvItemView
import kotlinx.android.synthetic.main.fragment_data_rv_item.view.*
import javax.inject.Inject
import javax.inject.Named

class DataRvAdapter(private val presenter : TabPresenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @field: [Inject Named("Picasso")]
    lateinit var imageLoader : IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DataHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_data_rv_item, parent, false)
        )
    }

    override fun getItemCount() = presenter.data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataHolder  = holder as? DataHolder
        dataHolder?.apply {
            itemPosition = position
            presenter.bind(this)
            itemView.setOnClickListener { presenter.clicked(position) }
        }
    }

    inner class DataHolder(private val view : View) : RecyclerView.ViewHolder(view), TabRvItemView {

        var itemPosition : Int = 0

        override fun setTitle(title: String) {
            view.rv_item_text.text = title
        }

        override fun setImage(url: String) {
            imageLoader.loadInto(url, view.rv_item_image, 20)
        }

    }

}