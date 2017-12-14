package com.broliveira.view

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.ContextThemeWrapper
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

  var title: CharSequence = ""
    set(value) {
      titleTextView.text = value
      field = value
    }

  var textColorResId: Int = R.color.white
    set(value) {
      titleTextView.textColor = ContextCompat.getColor(context, value)
      progressView.setColorResId(context, value)
      field = value
    }

  var textSizeSp: Int = context.sp(18)
    set(value) {
      titleTextView.textSize = context.sp(18).toFloat()
      field = value
    }

  var isLoading: Boolean = false
    set(value) {
      progressView.visibility = if (value) { View.VISIBLE } else { View.INVISIBLE }
      field = value
    }

  var buttonBacgroundDrawable: Drawable? = defaultBackgroundDrawable
  set(value) {
    backgroundDrawable = value
    field = value
  }

  private val defaultBackgroundDrawable: Drawable?
  get() {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      ContextCompat.getDrawable(context, R.drawable.main_ripple)
    } else {
      ContextCompat.getDrawable(context, R.drawable.main_ripple_compat)
    }
  }

  private lateinit var titleTextView: TextView

  private lateinit var progressView: ProgressBar

  val baseLayout = maxWidthRelativeLayout(dip(320)) {
    clipToPadding = false

    titleTextView = textView {
      typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
      textSize = this.textSize
      textAlignment = View.TEXT_ALIGNMENT_CENTER
      textSize = 18f
      textColor = ContextCompat.getColor(context, R.color.white)
    }.lparams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT) {
      centerInParent()
    }

    progressView = progressBar {
      this.setColorResId(context, R.color.white)
    }.lparams {
      alignParentRight()
      alignParentTop()
      alignParentBottom()
      topMargin = dip(15)
      bottomMargin = dip(15)
      rightMargin = dip(0)
    }
  }

  init {
    clipToPadding = false
    padding = dip(10)
    isClickable = true

    progressView.isIndeterminate = true
    progressView.visibility = View.INVISIBLE

    val params = LayoutParams(LayoutParams.MATCH_PARENT, dip(54)).apply {
      gravity = Gravity.CENTER
    }
    baseLayout.layoutParams = params

    ViewCompat.setElevation(baseLayout, context.dip(4).toFloat())
  }
}

fun defaultRippleDrawable(): RippleDrawable {
  return RippleDrawable(

  )
}

fun defaultButtonDrawable(): Drawable {

}


