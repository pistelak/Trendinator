package com.skypicker.trendinator.platform

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

interface IImageLoader {


    fun loadImage(context: Context, imageURL: String, imageView: ImageView)
}

class ImageLoader: IImageLoader {

    override fun loadImage(context: Context, imageURL: String, imageView: ImageView) {
        Glide
            .with(context)
            .load(imageURL)
            .centerCrop()
            .into(imageView)
    }

}