package com.junhwa.scrollableapplication.ui.like

import com.junhwa.domain.entity.Goods

interface LikeListener {
    fun updateGoodsLike(goods: Goods)
}