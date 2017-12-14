@file:Suppress("NOTHING_TO_INLINE")
package com.broliveira.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class MaxWidthLinearLayout : LinearLayout {
  private val mMaxWidth: Int

  constructor(context: Context, maxWidth: Int = 0) : super(context) {
    this.mMaxWidth = maxWidth
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    val a = getContext().obtainStyledAttributes(attrs, R.styleable.MaxWidthLayout)
    mMaxWidth = a.getDimensionPixelSize(R.styleable.MaxWidthLayout_maxWidth, Integer.MAX_VALUE)
    a.recycle()
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    var mutableWidthMeasureSpec = widthMeasureSpec
    val measuredWidth = MeasureSpec.getSize(mutableWidthMeasureSpec)
    if (mMaxWidth in 1..(measuredWidth - 1)) {
      val measureMode = MeasureSpec.getMode(mutableWidthMeasureSpec)
      mutableWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, measureMode)
    }
    super.onMeasure(mutableWidthMeasureSpec, heightMeasureSpec)
  }

  inline fun <T: View> T.lparams(
      c: Context?,
      attrs: AttributeSet?,
      init: LinearLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = LinearLayout.LayoutParams(c!!, attrs!!)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      c: Context?,
      attrs: AttributeSet?
  ): T {
    val layoutParams = LinearLayout.LayoutParams(c!!, attrs!!)
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
      height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
      init: LinearLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = LinearLayout.LayoutParams(width, height)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
      height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
  ): T {
    val layoutParams = LinearLayout.LayoutParams(width, height)
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
      height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
      weight: Float,
      init: LinearLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = LinearLayout.LayoutParams(width, height, weight)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
      height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
      weight: Float
  ): T {
    val layoutParams = LinearLayout.LayoutParams(width, height, weight)
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      p: ViewGroup.LayoutParams?,
      init: LinearLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = LinearLayout.LayoutParams(p!!)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      p: ViewGroup.LayoutParams?
  ): T {
    val layoutParams = LinearLayout.LayoutParams(p!!)
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      source: ViewGroup.MarginLayoutParams?,
      init: LinearLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = LinearLayout.LayoutParams(source!!)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      source: ViewGroup.MarginLayoutParams?
  ): T {
    val layoutParams = LinearLayout.LayoutParams(source!!)
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      source: LinearLayout.LayoutParams?,
      init: LinearLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = LinearLayout.LayoutParams(source!!)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      source: LinearLayout.LayoutParams?
  ): T {
    val layoutParams = LinearLayout.LayoutParams(source!!)
    this@lparams.layoutParams = layoutParams
    return this
  }
}
