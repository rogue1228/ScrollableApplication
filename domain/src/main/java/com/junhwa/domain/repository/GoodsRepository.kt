package com.junhwa.domain.repository

import com.junhwa.domain.entity.Banner
import com.junhwa.domain.entity.Goods
import com.junhwa.domain.entity.Home
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface GoodsRepository {
    fun getGoods(lastId: Int?): Single<List<Goods>>
    fun getLikeGoods(): Observable<List<Goods>>
    fun getBanners(): Single<List<Banner>>
    fun updateLikes(likes: Int)
}