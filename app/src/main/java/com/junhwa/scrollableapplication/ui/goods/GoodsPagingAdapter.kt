package com.junhwa.scrollableapplication.ui.goods

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import com.junhwa.domain.entity.Goods
import com.junhwa.scrollableapplication.R
import com.junhwa.scrollableapplication.databinding.ItemCardGoodsBinding

class GoodsPagingAdapter(private val likeGoodsClick: (Goods) -> Unit) :
    PagingDataAdapter<Goods, GoodsViewHolder>(goodsDiffItemCallback) {

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, likeGoodsClick)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val binding: ItemCardGoodsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_card_goods,
            parent,
            false
        )

        return GoodsViewHolder(binding)
    }
}