package com.junhwa.domain.usecase

import com.junhwa.domain.entity.Goods
import com.junhwa.domain.repository.GoodsRepository
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

class LikeGoodsUseCase(private val goodsRepository: GoodsRepository) {
    fun getLikeGoods(): Observable<List<Goods>> {
        return goodsRepository.getLikeGoods()
    }

    fun updateLikes(likes: Int) {
        goodsRepository.updateLikes(likes)
    }
}