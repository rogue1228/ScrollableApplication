package com.junhwa.data.repository

import com.junhwa.data.remote.RemoteDataSource
import com.junhwa.domain.entity.Banner
import com.junhwa.domain.entity.Goods
import com.junhwa.domain.entity.Home
import com.junhwa.domain.repository.GoodsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class GoodsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : GoodsRepository {
    private val goodsSubject: Subject<List<Goods>> = BehaviorSubject.create()
    private val likesSubject: Subject<List<Int>> = BehaviorSubject.create()

    override fun getHomeData(): Single<Home> {
        return remoteDataSource.initHome()
            .doOnSuccess {
                goodsSubject.onNext(it.goods)
            }
    }

    override fun getMoreGoods(lastId: Int): Single<List<Goods>> {
        return remoteDataSource.getGoods(lastId)
            .doOnSuccess {
                val origin = goodsSubject.blockingFirst()
                goodsSubject.onNext(origin.plus(it))
            }
    }

    override fun getLikeGoods(): Observable<List<Goods>> {
        return Observable.combineLatest(goodsSubject, likesSubject) { goodsList, likes ->
            goodsList.filter { likes.contains(it.id) }
        }
    }

    override fun updateLikes(likes: List<Int>) {
        likesSubject.onNext(likes)
    }
}