package com.broliveira.view.helper

import android.content.Context
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.widget.ProgressBar

fun ProgressBar.setColor(color: Int) {
  indeterminateDrawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
}

fun ProgressBar.setColorResId(context: Context, colorResId: Int)  {
  val color = ContextCompat.getColor(context, colorResId)
  setColor(color)
}