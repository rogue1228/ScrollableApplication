package com.junhwa.scrollableapplication.ui.like

import androidx.lifecycle.*
import com.junhwa.domain.entity.Goods
import com.junhwa.domain.usecase.LikeGoodsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class LikeViewModel(private val likeGoodsUseCase: LikeGoodsUseCase) : ViewModel(), LikeListener {

    val likeGoods: MutableLiveData<List<Goods>> = MutableLiveData()

    private var likeGoodsDataDisposable: Disposable? = null

    fun loadLikeGoods() {
        likeGoodsDataDisposable = likeGoodsUseCase.getLikeGoods()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                likeGoods.postValue(it)
            }
    }

    override fun updateGoodsLike(goods: Goods) {
        likeGoodsUseCase.updateLikes(goods.id)
    }

    fun dispose() {
        likeGoodsDataDisposable?.dispose()
    }
}