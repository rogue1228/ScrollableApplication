package com.junhwa.domain.usecase

import com.junhwa.domain.repository.GoodsRepository

class HomeUseCase(private val goodsRepository: GoodsRepository) {
    fun updateLikes(likes: IntArray) {
        goodsRepository.updateLikes(likes)
    }


}