package com.alex44.kotlintestapp.common.ui

import android.widget.ImageView
import com.alex44.kotlintestapp.common.interfaces.IImageLoader
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class PicassoImageLoader : IImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView, corners: Int) {
        val transformation : Transformation = RoundedCornersTransformation(corners, 0)
        Picasso.get()
            .load(url)
            .transform(transformation)
            .into(container)
    }

}