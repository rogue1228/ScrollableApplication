package com.junhwa.data

import com.junhwa.data.remote.RemoteDataSource
import com.junhwa.data.remote.RemoteDataSourceImpl
import com.junhwa.data.repository.GoodsRepositoryImpl
import com.junhwa.domain.repository.GoodsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import kotlin.test.Test

class RemoteDataSourceTest {
    private val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl(
        "http://d2bab9i9pr8lds.cloudfront.net",
        emptyList(),
        emptyList()
    )

    private val goodsRepo : GoodsRepository = GoodsRepositoryImpl(remoteDataSource)

    @Test
    fun homeDataTest() {
        goodsRepo.getHomeData().blockingGet().let { home ->
            home.banners.map {
                println(it)
            }

            home.goods.map {
                println(it)
            }
        }
    }

    @Test
    fun likeGoodsTest() {
        goodsRepo.getHomeData().blockingGet()
        goodsRepo.updateLikes(listOf(1,3,7))

        goodsRepo.getLikeGoods().blockingForEach { likeGoods ->
            likeGoods.map { println(it) }
        }
    }
}