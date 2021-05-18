package com.junhwa.cache

import android.content.Context
import android.content.SharedPreferences
import com.junhwa.domain.data_source.LocalDataSource

class LocalDataSourceImpl(context: Context): LocalDataSource {
    private val pref: SharedPreferences? by lazy { context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE) }

    override var likeGoodsIds: IntArray
        get() = pref?.getStringSet(LIKE_GOODS_IDS, null)?.let {
            it.map(String::toInt).toIntArray()
        } ?: IntArray(0)
        set(value) {
            value.map(Int::toString).let {
                pref?.edit()?.putStringSet(LIKE_GOODS_IDS, it.toSet())?.commit()
            }
        }

    companion object {
        private const val LIKE_GOODS_IDS = "like_goods_ids"
    }
}