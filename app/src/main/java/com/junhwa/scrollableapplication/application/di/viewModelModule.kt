package com.junhwa.scrollableapplication.application.di

import com.junhwa.scrollableapplication.ui.home.HomeViewModel
import com.junhwa.scrollableapplication.ui.like.LikeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get())}
    viewModel { LikeViewModel() }
}