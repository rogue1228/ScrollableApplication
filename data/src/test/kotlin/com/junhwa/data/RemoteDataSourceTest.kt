package com.junhwa.data

import com.junhwa.data.remote.RemoteDataSourceImpl
import com.junhwa.data.repository.GoodsRepositoryImpl
import com.junhwa.domain.data_source.RemoteDataSource
import com.junhwa.domain.repository.GoodsRepository
import kotlin.test.Test

class RemoteDataSourceTest {
    private val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl(
        "http://d2bab9i9pr8lds.cloudfront.net",
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
        goodsRepo.updateLikes(listOf(1,3,7,13).toIntArray())
        goodsRepo.getMoreGoods(10).blockingGet()

        goodsRepo.getLikeGoods().blockingForEach { likeGoods ->
            likeGoods.map { println(it) }
        }
    }
}