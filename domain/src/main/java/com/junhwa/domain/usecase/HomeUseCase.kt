package com.junhwa.domain.usecase

import com.junhwa.domain.entity.Banner
import com.junhwa.domain.entity.Goods
import com.junhwa.domain.entity.Home
import com.junhwa.domain.repository.GoodsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class HomeUseCase(private val goodsRepository: GoodsRepository) {
    fun updateLikes(likes: Int) {
        goodsRepository.updateLikes(likes)
    }

    fun getGoods(lastId: Int?): Single<List<Goods>> {
        return goodsRepository.getGoods(lastId)
    }

    fun getBanners(): Observable<List<Banner>> {
        return goodsRepository.getBanners()
    }
}