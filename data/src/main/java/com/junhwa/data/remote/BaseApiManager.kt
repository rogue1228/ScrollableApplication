package com.junhwa.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseApiManager(private val interceptors: List<Interceptor>) {

    open fun okHttpClientBuilder(): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()

        interceptors.forEach {
            httpClientBuilder.addInterceptor(it)
        }

        return httpClientBuilder
    }

    fun <T> create(baseUrl: String, service: Class<T>): T {
        val client = okHttpClientBuilder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(service)
    }
}