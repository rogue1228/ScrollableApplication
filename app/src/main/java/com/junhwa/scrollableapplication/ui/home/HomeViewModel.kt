package com.junhwa.scrollableapplication.ui.home

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.junhwa.domain.entity.Banner
import com.junhwa.domain.usecase.HomeUseCase
import com.junhwa.scrollableapplication.ui.goods.GoodsPagingSource
import com.junhwa.scrollableapplication.ui.home.banner.BannerListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel(), BannerListener {

    val goodsData = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 20,
            initialLoadSize = 10
        )
    ) {
        GoodsPagingSource(homeUseCase)
    }.flow.cachedIn(viewModelScope)

    val bannerData: MutableLiveData<List<Banner>> = MutableLiveData()

    init {
        homeUseCase.getBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data, error ->
                bannerData.postValue(data)
                _totalCount.postValue(data.size)
            }
    }

    private val _totalCount: MutableLiveData<Int> = MutableLiveData()
    private val _currentCount: MutableLiveData<Int> = MutableLiveData()

    override fun totalCount(): LiveData<Int> = _totalCount

    override fun currentItemPosition(): LiveData<Int> = _currentCount

    fun updateBannerPageChange(position: Int) {
        _currentCount.value = position + 1
    }
}