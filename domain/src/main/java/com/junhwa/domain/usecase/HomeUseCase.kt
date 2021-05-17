package com.junhwa.domain.usecase

import com.junhwa.domain.entity.Goods
import com.junhwa.domain.entity.Home
import com.junhwa.domain.repository.GoodsRepository
import io.reactivex.rxjava3.core.Single

class HomeUseCase(private val goodsRepository: GoodsRepository) {
    fun updateLikes(likes: IntArray) {
        goodsRepository.updateLikes(likes)
    }

    fun getHomeData(): Single<Home> {
        return goodsRepository.getHomeData()
    }

    fun getMoreGoods(lastId: Int): Single<List<Goods>> {
        return goodsRepository.getMoreGoods(lastId)
    }
}