package com.junhwa.scrollableapplication.ui.goods

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.junhwa.domain.entity.Goods
import com.junhwa.domain.usecase.HomeUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single

class GoodsPagingSource(private val homeUseCase: HomeUseCase) : RxPagingSource<Int, Goods>() {
    override fun getRefreshKey(state: PagingState<Int, Goods>): Int? {
        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Goods>> {
        val currentPagingKey = params.key

        return homeUseCase.getGoods(currentPagingKey)
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                LoadResult.Page(
                    data = it,
                    prevKey = currentPagingKey,
                    nextKey = if (it.isEmpty()) null else it.last().id
                )
            }
    }

}