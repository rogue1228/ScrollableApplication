package com.junhwa.data.remote

import com.junhwa.domain.data_source.RemoteDataSource
import com.junhwa.domain.entity.Goods
import com.junhwa.domain.entity.Home
import io.reactivex.rxjava3.core.Single
import okhttp3.Interceptor

class RemoteDataSourceImpl(baseUrl: String, interceptors: List<Interceptor>) :
    BaseApiManager(interceptors),
    RemoteDataSource {

    private val service: ApiService = create(baseUrl, ApiService::class.java)

    override fun initHome(): Single<Home> {
        return service.getHome()
            .map { ApiMapper.jsonElementToHome(it) }
    }

    override fun getGoods(lastId: Int): Single<List<Goods>> {
        return service.getGoods(lastId)
            .map { ApiMapper.jsonElementToGoodsList(it) }
    }

}