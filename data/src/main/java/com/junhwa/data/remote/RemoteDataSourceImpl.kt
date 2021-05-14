package com.junhwa.data.remote

import com.junhwa.domain.entity.Goods
import com.junhwa.domain.entity.Home
import io.reactivex.rxjava3.core.Single
import okhttp3.Interceptor

class RemoteDataSourceImpl(baseUrl: String, networkInterceptors: List<Interceptor>, interceptors: List<Interceptor>) :
    BaseApiManager(networkInterceptors = networkInterceptors, interceptors = interceptors),
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