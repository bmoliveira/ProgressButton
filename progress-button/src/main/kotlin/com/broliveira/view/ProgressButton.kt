package com.broliveira.view

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import org.jetbrains.anko.*


class ProgressButton(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int) : FrameLayout(context, attrs) {

  constructor(context: Context): this(context, null, 0 , 0)
  constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0 , 0)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr, 0)

  var title: CharSequence = "Sign In"
    set(value) {
      titleTextView.text = value
      field = value
    }

  var titleColor: Int = ContextCompat.getColor(context, android.R.color.white)
    set(value) {
      titleTextView.textColor = value
      progressView.setColor(value)
      field = value
    }

  var titleTypeFace: Typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
    set(value) {
      titleTextView.typeface = value
      field = value
    }

  var titleTextSize: Float = context.sp(9).toFloat()
    set(value) {
      titleTextView.textSize = value
      field = value
    }

  var rippleBackgroundColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
    set(value) {
      field = value
      baseLayout.background = defaultBackgroundDrawable
    }

  var rippleSecondaryColor = ContextCompat.getColor(context, android. R.color.holo_red_light)
    set(value) {
      field = value
      baseLayout.background = defaultBackgroundDrawable
    }

  var minHeight: Int = dip(42)
    set(value) {
      titleTextView.minHeight = value
      field = value
    }

  private val maxWidth: Int = Integer.MAX_VALUE
  get() {
    return field - internalMargin*2
  }

  var buttonElevation: Float = context.dip(4).toFloat()
    set(value) {
      ViewCompat.setElevation(baseLayout, value)
      field = value
    }

  var isLoading: Boolean = true
    set(value) {
      progressView.visibility = if (value) { View.VISIBLE } else { View.INVISIBLE }
      field = value
    }

  private val internalMargin = dip(5)

  private val defaultBackgroundDrawable: Drawable?
    get() = DrawableHelper.getSelectableDrawableFor(rippleBackgroundColor, rippleSecondaryColor, dip(30).toFloat())

  private lateinit var titleTextView: TextView

  private lateinit var progressView: ProgressBar

  val baseLayout = maxWidthRelativeLayout(maxWidth) {
    clipToPadding = false

    titleTextView = textView {
      textAlignment = View.TEXT_ALIGNMENT_CENTER
      gravity = Gravity.CENTER
    }.lparams(width = RelativeLayout.LayoutParams.MATCH_PARENT, height = RelativeLayout.LayoutParams.WRAP_CONTENT) {
      centerHorizontally()
      centerVertically()
      marginStart = dip(35)
      marginEnd = dip(35)
      topPadding = dip(10)
      bottomPadding = dip(10)
    }

    progressView = progressBar {
      setColor(titleColor)
    }.lparams(dip(30), dip(30)){
      alignParentRight()
      centerVertically()
      rightPadding = dip(10)
    }
  }

  init {
    isClickable = true

    progressView.isIndeterminate = true
    progressView.visibility = View.INVISIBLE

    val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        .apply {
          gravity = Gravity.CENTER
          margin = internalMargin
        }

    baseLayout.layoutParams = params
    updateValues()
  }

  private fun updateValues() {
    this.title = this.title
    this.titleColor = this.titleColor
    this.titleTypeFace = this.titleTypeFace
    this.titleTextSize = this.titleTextSize
    this.rippleBackgroundColor = this.rippleBackgroundColor
    this.rippleSecondaryColor = this.rippleSecondaryColor
    this.minHeight = this.minHeight
    this.buttonElevation = this.buttonElevation
    this.isLoading = this.isLoading
    this.invalidate()
    this.requestLayout()
  }
}

