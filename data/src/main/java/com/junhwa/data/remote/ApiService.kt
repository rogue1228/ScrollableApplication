package com.junhwa.data.remote

import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/home")
    fun getHome(): Single<JsonElement>

    @GET("/api/home/goods")
    fun getGoods(@Query("lastId") lastId: Int): Single<JsonElement>
}