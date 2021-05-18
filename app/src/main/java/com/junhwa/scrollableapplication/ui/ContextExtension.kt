package com.junhwa.scrollableapplication.ui

import android.content.Context

fun Context.dip(size: Float): Float {
    return resources.displayMetrics.density * size
}

fun Context.dipToInt(size: Float): Int {
    return dip(size).toInt()
}
