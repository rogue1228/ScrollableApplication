package com.junhwa.scrollableapplication.ui.home.banner

import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junhwa.domain.entity.Banner

class BannerPagerAdapter : RecyclerView.Adapter<BannerPagerAdapter.BannerViewHolder>() {

    private var bannerList: List<Banner> = emptyList()

    class BannerViewHolder(private val view: ImageView) : RecyclerView.ViewHolder(view) {
        fun bind(banner: Banner) {
            Glide.with(view)
                .load(banner.image)
                .into(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(AppCompatImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            adjustViewBounds = true
        })
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(bannerList[position])
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

    fun setBanner(list: List<Banner>?) {
        bannerList = list ?: emptyList()
        notifyDataSetChanged()
    }
}