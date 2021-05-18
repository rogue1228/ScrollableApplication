package com.junhwa.data.repository

import com.junhwa.domain.data_source.RemoteDataSource
import com.junhwa.domain.entity.Banner
import com.junhwa.domain.entity.Goods
import com.junhwa.domain.repository.GoodsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class GoodsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : GoodsRepository {
    private val goodsSubject: Subject<List<Goods>> = BehaviorSubject.create()
    private val likesSubject: Subject<IntArray> = BehaviorSubject.create()

    override fun getGoods(lastId: Int?): Single<List<Goods>> {
        return if (lastId == null) {
            remoteDataSource.initHome()
                .map { it.goods }
        } else {
            remoteDataSource.getGoods(lastId)
        }
    }

    override fun getLikeGoods(): Observable<List<Goods>> {
        return Observable.combineLatest(goodsSubject, likesSubject) { goodsList, likes ->
            goodsList.filter { likes.contains(it.id) }
        }
    }

    override fun updateLikes(likes: IntArray) {
        likesSubject.onNext(likes)
    }

    override fun getBanners(): Single<List<Banner>> {
        return remoteDataSource.initHome()
            .map { it.banners }
    }
}