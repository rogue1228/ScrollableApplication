package com.junhwa.scrollableapplication.ui.home

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.junhwa.domain.entity.Banner
import com.junhwa.domain.entity.Goods
import com.junhwa.domain.usecase.HomeUseCase
import com.junhwa.scrollableapplication.ui.goods.GoodsPagingSource
import com.junhwa.scrollableapplication.ui.home.banner.BannerListener
import com.junhwa.scrollableapplication.ui.like.LikeListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel(), BannerListener, LikeListener {

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

    private var bannerDataDisposable: Disposable? = null

    fun loadBanner() {
        bannerDataDisposable = homeUseCase.getBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                Log.d("receive data", data.toString())
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

    override fun updateGoodsLike(goods: Goods) {
        homeUseCase.updateLikes(goods.id)
    }

    fun dispose() {
        bannerDataDisposable?.dispose()
    }
}