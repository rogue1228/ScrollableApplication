package com.junhwa.data.remote

import com.junhwa.domain.entity.Goods
import com.junhwa.domain.entity.Home
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun initHome(): Single<Home>
    fun getGoods(lastId: Int): Single<List<Goods>>
}