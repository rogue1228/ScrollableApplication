package com.junhwa.scrollableapplication.ui.goods

import androidx.recyclerview.widget.RecyclerView
import com.junhwa.domain.entity.Goods
import com.junhwa.scrollableapplication.databinding.ItemCardGoodsBinding

class GoodsViewHolder(private val binding: ItemCardGoodsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(goods: Goods, likeGoodsClick: (Goods) -> Unit) {
        binding.goods = goods
        binding.goodsLikeToggle.setOnClickListener {
            val enable = !it.isSelected
            it.isSelected = enable
            goods.updateLike(enable)
            likeGoodsClick.invoke(goods)
        }
    }
}