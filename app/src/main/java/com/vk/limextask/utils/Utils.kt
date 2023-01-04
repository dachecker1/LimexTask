package com.vk.limextask.utils

import android.view.View

object Utils {

    fun View.setDebouncedClickListener(
        debounceIntervalMs: Int = 500,
        listener: (view: View?) -> Unit
    ) {
        var lastTapTimestamp: Long = 0
        val customListener = View.OnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTapTimestamp > debounceIntervalMs) {
                lastTapTimestamp = currentTime
                listener(it)
            }
        }
        this.setOnClickListener(customListener)
    }
}