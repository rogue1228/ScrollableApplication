package com.junhwa.scrollableapplication.ui.goods

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.junhwa.domain.entity.Goods
import com.junhwa.scrollableapplication.R
import com.junhwa.scrollableapplication.databinding.ItemCardGoodsBinding

class GoodsAdapter(private val likeClick: (Goods) -> Unit) :
    PagingDataAdapter<Goods, GoodsAdapter.GoodsViewHolder>(goodsDiffUtil) {

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, likeClick)
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

    class GoodsViewHolder(private val binding: ItemCardGoodsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(goods: Goods, likeClick: (Goods) -> Unit) {
            binding.goods = goods


        }
    }

    companion object {
        val goodsDiffUtil = object : DiffUtil.ItemCallback<Goods>() {
            override fun areItemsTheSame(oldItem: Goods, newItem: Goods): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Goods, newItem: Goods): Boolean {
                return oldItem == newItem
            }
        }
    }
}