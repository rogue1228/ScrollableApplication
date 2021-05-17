package com.junhwa.scrollableapplication.application.di

import com.junhwa.domain.usecase.HomeUseCase
import com.junhwa.domain.usecase.LikeGoodsUseCase
import org.koin.dsl.module

val usecaseModule = module {
    single {
        HomeUseCase(get())
    }

    single {
        LikeGoodsUseCase(get())
    }
}