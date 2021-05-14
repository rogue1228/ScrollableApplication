package com.junhwa.domain.repository

import com.junhwa.domain.entity.Goods
import com.junhwa.domain.entity.Home
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface GoodsRepository {
    fun getHomeData(): Single<Home>
    fun getMoreGoods(lastId: Int): Single<List<Goods>>
    fun getLikeGoods(): Observable<List<Goods>>
}