package com.junhwa.scrollableapplication.application.di

import com.junhwa.scrollableapplication.ui.home.HomeViewModel
import com.junhwa.scrollableapplication.ui.like.LikeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel(get())}
    single { LikeViewModel() }
}