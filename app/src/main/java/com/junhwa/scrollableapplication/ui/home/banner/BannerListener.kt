package com.junhwa.scrollableapplication.ui.home.banner

import androidx.lifecycle.LiveData

interface BannerListener {
    fun totalCount(): LiveData<Int>
    fun currentItemPosition(): LiveData<Int>
}