package com.junhwa.scrollableapplication.application.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.junhwa.cache.LocalDataSourceImpl
import com.junhwa.data.remote.RemoteDataSourceImpl
import com.junhwa.domain.data_source.LocalDataSource
import com.junhwa.domain.data_source.RemoteDataSource
import com.junhwa.scrollableapplication.BuildConfig
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        ChuckerCollector(
            context = get(),
            showNotification = BuildConfig.DEBUG,
            retentionPeriod = RetentionManager.Period.ONE_DAY
        )
    }

    single<LocalDataSource> { LocalDataSourceImpl(get()) }
    single<RemoteDataSource> {
        RemoteDataSourceImpl(
            baseUrl = BuildConfig.BASE_API_URL,
            interceptors = listOf(
                ChuckerInterceptor.Builder(get())
                    .maxContentLength(250_000L)
                    .collector(get())
                    .build()
            )
        )
    }
}