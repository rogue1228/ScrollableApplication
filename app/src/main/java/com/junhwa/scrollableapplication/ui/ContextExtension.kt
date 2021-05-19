package com.junhwa.scrollableapplication.ui

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

fun Context.dip(size: Float): Float {
    return resources.displayMetrics.density * size
}

fun Context.dipToInt(size: Float): Int {
    return dip(size).toInt()
}

fun Context.getDisplayWidth(): Int {
    // display = getDisplay()
    // api 버전 30부턴 변경 되어야 함.
    val display = getSystemService(WindowManager::class.java).defaultDisplay

    val size = Point()
    display.getRealSize(size)
    return size.x
}