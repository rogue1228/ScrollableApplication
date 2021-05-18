package com.junhwa.scrollableapplication.ui.like

import androidx.lifecycle.LiveData
import com.junhwa.domain.entity.Goods

interface LikeListener {
    fun updateGoodsLike(goods: Goods)
}