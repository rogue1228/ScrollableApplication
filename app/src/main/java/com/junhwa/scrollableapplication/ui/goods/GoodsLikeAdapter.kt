package com.junhwa.scrollableapplication.ui.goods

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.junhwa.domain.entity.Goods
import com.junhwa.scrollableapplication.R
import com.junhwa.scrollableapplication.databinding.ItemCardGoodsBinding

class GoodsLikeAdapter(private val likeGoodsClick: (Goods) -> Unit) :
    RecyclerView.Adapter<GoodsViewHolder>() {

    private var likeGoodsList: List<Goods> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val binding: ItemCardGoodsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_card_goods,
            parent,
            false
        )

        return GoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        likeGoodsList[position].let {
            holder.bind(it, likeGoodsClick)
        }
    }

    override fun getItemCount(): Int {
        return likeGoodsList.size
    }

    fun setLikeGoods(list: List<Goods>?) {
        val newList = list ?: emptyList()

        val result = DiffUtil.calculateDiff(goodsDiffCallback(newList, likeGoodsList))
        likeGoodsList = newList
        result.dispatchUpdatesTo(this)
    }
}