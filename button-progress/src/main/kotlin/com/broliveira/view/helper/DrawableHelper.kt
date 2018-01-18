package com.broliveira.view.helper

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build

object DrawableHelper {

  fun getSelectableDrawableFor(
      defaultColor: Int,
      pressedColor: Int = lightenOrDarken(defaultColor, 0.20),
      cornerRadius: Float = 0f
  ): Drawable
      = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    StateListDrawable()
        .apply {
          val focusedColor = lightenOrDarken(pressedColor, 0.20)
          addState(intArrayOf(android.R.attr.state_pressed), getRoundRectShapeDrawable(pressedColor, cornerRadius))
          addState(intArrayOf(android.R.attr.state_focused), getRoundRectShapeDrawable(focusedColor, cornerRadius))
          addState(intArrayOf(), getRoundRectShapeDrawable(defaultColor, cornerRadius))
        }
  } else {
    val pressedStates = ColorStateList.valueOf(pressedColor)
    val mainBackground = getRoundRectShapeDrawable(defaultColor, cornerRadius)
    val mask = getRoundRectShapeDrawable(defaultColor, cornerRadius)
    RippleDrawable(pressedStates, mainBackground, mask)
  }

  fun getRoundRectShapeDrawable(color: Int, cornerRadius: Float): Drawable {
    if (cornerRadius <= 0) {
      return ShapeDrawable(RectShape()).also { it.paint.color = color }
    }

    return FloatArray(8).also { it.fill(cornerRadius) }
        .let { RoundRectShape(it, null, null) }
        .let { ShapeDrawable(it) }
        .apply { paint.color = color }
  }

  private fun lightenOrDarken(color: Int, fraction: Double): Int {
    return if (canLighten(color, fraction)) {
      lighten(color, fraction)
    } else {
      darken(color, fraction)
    }
  }

  private fun lighten(color: Int, fraction: Double): Int {
    var red = Color.red(color)
    var green = Color.green(color)
    var blue = Color.blue(color)
    red = lightenColor(red, fraction)
    green = lightenColor(green, fraction)
    blue = lightenColor(blue, fraction)
    val alpha = Color.alpha(color)
    return Color.argb(alpha, red, green, blue)
  }

  private fun darken(color: Int, fraction: Double): Int {
    var red = Color.red(color)
    var green = Color.green(color)
    var blue = Color.blue(color)
    red = darkenColor(red, fraction)
    green = darkenColor(green, fraction)
    blue = darkenColor(blue, fraction)
    val alpha = Color.alpha(color)

    return Color.argb(alpha, red, green, blue)
  }

  private fun canLighten(color: Int, fraction: Double): Boolean {
    val red = Color.red(color)
    val green = Color.green(color)
    val blue = Color.blue(color)
    return (canLightenComponent(red, fraction)
        && canLightenComponent(green, fraction)
        && canLightenComponent(blue, fraction))
  }

  private fun canLightenComponent(colorComponent: Int, fraction: Double): Boolean {
    val red = Color.red(colorComponent)
    val green = Color.green(colorComponent)
    val blue = Color.blue(colorComponent)
    return (red + red * fraction < 255
        && green + green * fraction < 255
        && blue + blue * fraction < 255)
  }

  private fun darkenColor(color: Int, fraction: Double): Int {
    return Math.max(color - color * fraction, 0.0).toInt()
  }

  private fun lightenColor(color: Int, fraction: Double): Int {
    return Math.min(color + color * fraction, 255.0).toInt()
  }
}