package com.junhwa.scrollableapplication.ui.home.banner

import androidx.lifecycle.LiveData
import com.junhwa.domain.entity.Banner

interface BannerListener {
    fun totalCount(): LiveData<Int>
    fun currentItemPosition(): LiveData<Int>
}