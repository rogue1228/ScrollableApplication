package com.junhwa.scrollableapplication.application.di

import com.junhwa.data.repository.GoodsRepositoryImpl
import com.junhwa.domain.repository.GoodsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<GoodsRepository> { GoodsRepositoryImpl(get()) }
}