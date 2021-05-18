package com.junhwa.scrollableapplication.ui.goods

import androidx.recyclerview.widget.DiffUtil
import com.junhwa.domain.entity.Goods

val goodsDiffItemCallback = object : DiffUtil.ItemCallback<Goods>() {
    override fun areItemsTheSame(oldItem: Goods, newItem: Goods): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Goods, newItem: Goods): Boolean {
        return oldItem == newItem
    }
}

fun goodsDiffCallback(newList: List<Goods>, oldList: List<Goods>) = object : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].hasLike() == newList[newItemPosition].hasLike()
    }

}