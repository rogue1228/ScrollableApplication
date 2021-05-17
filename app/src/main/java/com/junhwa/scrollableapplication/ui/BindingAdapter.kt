package com.junhwa.scrollableapplication.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("image_url")
fun imageLoad(view: ImageView, imageUrl: String) {
    val width = view.width
    val height = (width * 1.2).toInt()

    Glide
        .with(view)
        .load(imageUrl)
        .override(width, height)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(view)
}