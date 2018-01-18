package com.broliveira.view

import android.content.Context
import android.graphics.Point
import android.view.Gravity
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.windowManager

inline fun ViewManager.formRelativeLayout(
    context: Context,
    init: (MaxWidthRelativeLayout.() -> Unit) = {}
): MaxWidthRelativeLayout {
  val display = context.windowManager.defaultDisplay
  val size = Point()
  display.getSize(size)
  return maxWidthRelativeLayout(Math.min(size.x, size.y), init)
}

inline fun ViewManager.maxWidthRelativeLayout(maxWidth: Int = Integer.MAX_VALUE) = maxWidthRelativeLayout(maxWidth) {}
inline fun ViewManager.maxWidthRelativeLayout(
    maxWidth: Int = Integer.MAX_VALUE,
    init: MaxWidthRelativeLayout.() -> Unit
): MaxWidthRelativeLayout
    = ankoView({ MaxWidthRelativeLayout(it, maxWidth).also { it.gravity = Gravity.CENTER_HORIZONTAL } }, 0, init)
