package com.junhwa.scrollableapplication.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

@BindingAdapter("image_url")
fun imageLoad(view: ImageView, imageUrl: String) {
    val width = view.width
    val height = (width * 1.2).toInt()
    val radius = view.context.dipToInt(4f)

    Glide
        .with(view)
        .load(imageUrl)
        .override(width, height)
        .transform(RoundedCorners(radius))
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(view)
}

@BindingAdapter("goods_like")
fun goodsLike(view: ImageView, value: Boolean) {
    view.isSelected = value
}