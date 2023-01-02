package com.vk.limextask.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.drawable.DrawableCompat

object AndroidUtils {

    fun getTintedDrawable(context: Context, drawableId: Int, colorId: Int): Drawable? {
        val tintedDrawable = getDrawable(context, drawableId)
        tintedDrawable?.let {
            DrawableCompat.setTint(tintedDrawable.mutate(), getColor(context, colorId))
        }
        return tintedDrawable
    }
}