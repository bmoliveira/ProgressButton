@file:Suppress("NOTHING_TO_INLINE")
package com.broliveira.view

import android.content.Context
import android.graphics.Point
import android.view.Gravity
import android.view.ViewManager
import android.widget.LinearLayout
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.windowManager

inline fun ViewManager.formLinearLayout(
    context: Context,
    init: (MaxWidthLinearLayout.() -> Unit) = {}
): LinearLayout {
  val display = context.windowManager.defaultDisplay
  val size = Point()
  display.getSize(size)
  return maxWidthLinearLayout(Math.min(size.x, size.y), init)
}

inline fun ViewManager.maxWidthLinearLayout(maxWidth: Int = Integer.MAX_VALUE) = maxWidthLinearLayout(maxWidth) {}
inline fun ViewManager.maxWidthLinearLayout(
    maxWidth: Int = Integer.MAX_VALUE,
    init: MaxWidthLinearLayout.() -> Unit
): LinearLayout
    = ankoView(
    {
      MaxWidthLinearLayout(it, maxWidth).also { it.gravity = Gravity.CENTER_HORIZONTAL }
    },0, init)
