package net.alfdev.xapotestcode.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable

class DrawablesUtility {
    companion object {
        fun colorizeDrawable(drawable: Drawable, color: String): Drawable {
            drawable.setColorFilter(Color.parseColor(color), PorterDuff.Mode.ADD)

            return drawable
        }
    }
}