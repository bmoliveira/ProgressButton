package com.broliveira.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.broliveira.view.maxwidthlayout.R

open class MaxWidthRelativeLayout: RelativeLayout {
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
      init: RelativeLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = RelativeLayout.LayoutParams(c!!, attrs!!)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      c: Context?,
      attrs: AttributeSet?
  ): T {
    val layoutParams = RelativeLayout.LayoutParams(c!!, attrs!!)
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
      height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
      init: RelativeLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = RelativeLayout.LayoutParams(width, height)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
      height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
  ): T {
    val layoutParams = RelativeLayout.LayoutParams(width, height)
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      source: ViewGroup.LayoutParams?,
      init: RelativeLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = RelativeLayout.LayoutParams(source!!)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      source: ViewGroup.LayoutParams?
  ): T {
    val layoutParams = RelativeLayout.LayoutParams(source!!)
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      source: ViewGroup.MarginLayoutParams?,
      init: RelativeLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = RelativeLayout.LayoutParams(source!!)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      source: ViewGroup.MarginLayoutParams?
  ): T {
    val layoutParams = RelativeLayout.LayoutParams(source!!)
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      source: RelativeLayout.LayoutParams?,
      init: RelativeLayout.LayoutParams.() -> Unit
  ): T {
    val layoutParams = RelativeLayout.LayoutParams(source!!)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
  }

  inline fun <T: View> T.lparams(
      source: RelativeLayout.LayoutParams?
  ): T {
    val layoutParams = RelativeLayout.LayoutParams(source!!)
    this@lparams.layoutParams = layoutParams
    return this
  }
}
