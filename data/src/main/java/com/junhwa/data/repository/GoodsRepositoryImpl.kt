package com.junhwa.data.repository

import com.junhwa.domain.data_source.LocalDataSource
import com.junhwa.domain.data_source.RemoteDataSource
import com.junhwa.domain.entity.Banner
import com.junhwa.domain.entity.Goods
import com.junhwa.domain.repository.GoodsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class GoodsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : GoodsRepository {
    private val goodsSubject: Subject<List<Goods>> = BehaviorSubject.create()
    private val likesSubject: Subject<IntArray> =
        BehaviorSubject.createDefault(localDataSource.likeGoodsIds)

    private val likeGoodsList: MutableList<Goods> = mutableListOf()

    override fun getGoods(lastId: Int?): Single<List<Goods>> {
        val goodsData = if (lastId == null) {
            likeGoodsList.clear()
            remoteDataSource.initHome()
                .map { it.goods }
        } else {
            remoteDataSource.getGoods(lastId)
        }

        val likeGoodsIds = localDataSource.likeGoodsIds

        return goodsData.map { goodsList ->
            val likeGoods = goodsList.map { goods ->
                goods.updateLike(likeGoodsIds.contains(goods.id))
            }

            likeGoodsList.addAll(likeGoods)
            goodsSubject.onNext(likeGoodsList)

            likeGoods
        }.subscribeOn(Schedulers.io())
    }

    override fun getLikeGoods(): Observable<List<Goods>> {
        likesSubject.onNext(localDataSource.likeGoodsIds)

        return Observable.combineLatest(goodsSubject, likesSubject) { goodsList, likes ->
            goodsList.filter { likes.contains(it.id) }
        }
    }

    override fun updateLikes(likes: Int) {
        val mutableData = localDataSource.likeGoodsIds.toMutableList()

        if (mutableData.contains(likes)) {
            mutableData.remove(likes)
        } else {
            mutableData.add(likes)
        }

        val newData = mutableData.toIntArray()

        localDataSource.likeGoodsIds = newData
        likesSubject.onNext(newData)
    }

    override fun getBanners(): Single<List<Banner>> {
        return remoteDataSource.initHome()
            .map { it.banners }
    }
}