package com.alex44.kotlintestapp.di.modules

import android.widget.ImageView
import com.alex44.kotlintestapp.common.interfaces.IImageLoader
import com.alex44.kotlintestapp.common.ui.GlideImageLoader
import com.alex44.kotlintestapp.common.ui.PicassoImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ImageModule {

    @Named("Glide")
    @Provides
    fun glideImageLoader() : IImageLoader<ImageView> = GlideImageLoader()

    @Named("Picasso")
    @Provides
    fun picassoImageLoader() : IImageLoader<ImageView> = PicassoImageLoader()

}