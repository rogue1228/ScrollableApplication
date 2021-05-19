package com.junhwa.scrollableapplication.ui

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import com.junhwa.scrollableapplication.BuildConfig

fun Context.dip(size: Float): Float {
    return resources.displayMetrics.density * size
}

fun Context.dipToInt(size: Float): Int {
    return dip(size).toInt()
}

fun Context.getDisplayWidth(): Int {
    val display = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        display
    } else {
        getSystemService(WindowManager::class.java).defaultDisplay
    }

    val size = Point()
    display?.getRealSize(size)
    return size.x
}