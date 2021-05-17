package com.junhwa.scrollableapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.junhwa.domain.usecase.HomeUseCase
import com.junhwa.scrollableapplication.ui.goods.GoodsPagingSource

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    val goodsData = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 20,
            initialLoadSize = 10
        )
    ) {
        GoodsPagingSource(homeUseCase)
    }.flow.cachedIn(viewModelScope)

}