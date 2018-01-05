package com.broliveira.view

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.broliveira.view.progressbutton.R
import org.jetbrains.anko.*

class ProgressButton(
    context: Context,
    private val attrs: AttributeSet?,
    private val defStyleAttr: Int,
    private val defStyleRes: Int) : FrameLayout(context, attrs) {

  constructor(context: Context): this(context, null, 0 , 0)
  constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0, 0)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr, 0)

  var title: CharSequence = defaultTitle
    set(value) {
      field = value
      titleTextView.text = value
    }

  var titleColor: Int = defaultTitleColor
    set(value) {
      field = value
      titleTextView.textColor = value
      progressView.setColor(value)
    }

  var titleTypeFace: Typeface = Typeface.create(typefaceName, Typeface.NORMAL)
    set(value) {
      titleTextView.typeface = value
      field = value
    }

  var titleTextSize: Float = defaultTitleTextSize
    set(value) {
      field = value
      titleTextView.textSize = value
    }

  var rippleBackgroundColor = defaultRippleBackgroundColor
    set(value) {
      field = value
      updateBackground()
    }

  var rippleSecondaryColor = defaultRippleSecondaryColor
    set(value) {
      field = value
      updateBackground()
    }

  var minHeight: Int = defaultMinHeight
    set(value) {
      field = value
      titleTextView.minHeight = value
    }

  private val maxWidth: Int = defaultMaxWidth
    get() =  field - internalMargin*2

  var buttonRadius: Float = defaultButtonRadius
  set(value) {
    field = value
    updateBackground()
  }

  var buttonElevation: Float = defaultButtonElevation
    set(value) {
      field = value
      ViewCompat.setElevation(baseLayout, value)
      updateMargins()
    }

  var isLoading: Boolean = defaultIsLoading
    set(value) {
      field = value
      progressView.visibility = if (value) { View.VISIBLE } else { View.INVISIBLE }
    }

  private val internalMargin: Int = (buttonElevation * 1.5).toInt()

  private lateinit var titleTextView: TextView

  private lateinit var progressView: ProgressBar

  private val baseLayout = maxWidthRelativeLayout(maxWidth) {
    titleTextView = textView {
      textAlignment = View.TEXT_ALIGNMENT_CENTER
      gravity = Gravity.CENTER
    }.lparams(width = RelativeLayout.LayoutParams.MATCH_PARENT, height = RelativeLayout.LayoutParams.WRAP_CONTENT) {
      alignParentStart()
      alignParentEnd()
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
    updateValues()
  }

  private fun updateMargins() {
    val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        .apply {
          gravity = Gravity.CENTER
          margin = internalMargin
        }
    baseLayout.layoutParams = params
    this.invalidate()
  }

  private fun updateBackground() {
    baseLayout.background = defaultBackgroundDrawable
  }

  private fun updateValues() {
    this.title = this.title
    this.titleColor = this.titleColor
    this.titleTypeFace = this.titleTypeFace
    this.titleTextSize = this.titleTextSize
    this.minHeight = this.minHeight
    this.buttonElevation = this.buttonElevation
    this.isLoading = this.isLoading
    updateBackground()
    this.invalidate()
    this.requestLayout()
  }

  private val defaultBackgroundDrawable: Drawable?
    get() = DrawableHelper.getSelectableDrawableFor(rippleBackgroundColor, rippleSecondaryColor, defaultButtonRadius)

  private val defaultTitle: String
    get() = getAttributeStringValue(R.styleable.ProgressButton_pbTitle, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)

  private val typefaceName: String
    get() = getAttributeStringValue(R.styleable.ProgressButton_pbTitleTypeFaceName, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)

  private val defaultTitleTextSize: Float
    get() = getAttributeFloatValue(R.styleable.ProgressButton_pbTitleTextSize, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)
        ?: context.sp(9).toFloat()

  private val defaultTitleColor: Int
    get() = getAttributeColorValue(R.styleable.ProgressButton_pbTitleColor, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)
        ?: getThemeAttributeColor(android.R.attr.textColor, context)
        ?: getThemeAttributeColorByName("textColor", context)
        ?: ContextCompat.getColor(context, android.R.color.white)

  private val defaultRippleBackgroundColor: Int
    get() = getAttributeColorValue(R.styleable.ProgressButton_pbRippleBackgroundColor, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)
        ?: getThemeAttributeColor(android.R.attr.colorPrimary, context)
        ?: getThemeAttributeColorByName("colorPrimary", context)
        ?: ContextCompat.getColor(context, android.R.color.holo_red_dark)

  private val defaultRippleSecondaryColor: Int
    get() = getAttributeColorValue(R.styleable.ProgressButton_pbRippleSecondaryColor, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)
        ?: getThemeAttributeColor(android.R.attr.colorPrimaryDark, context)
        ?: getThemeAttributeColorByName("colorPrimaryDark", context)
        ?: ContextCompat.getColor(context, android.R.color.holo_red_light)

  private val defaultButtonRadius: Float
    get() = getAttributeFloatValue(R.styleable.ProgressButton_pbButtonRadius, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)
        ?: dip(30).toFloat()

  private val defaultMinHeight: Int
    get() = getAttributeFloatValue(R.styleable.ProgressButton_pbMinHeight, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)?.toInt()
        ?: dip(42)

  private val defaultMaxWidth: Int
    get() = getAttributeFloatValue(R.styleable.ProgressButton_pbMaxWidth, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)?.toInt()
        ?: Integer.MAX_VALUE

  private val defaultButtonElevation: Float
    get() = getAttributeFloatValue(R.styleable.ProgressButton_pbButtonElevation, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)
        ?: context.dip(4).toFloat()

  private val defaultIsLoading: Boolean
    get() = getAttributeBooleanValue(R.styleable.ProgressButton_pbIsLoading, attrs, context, R.styleable.ProgressButton, defStyleAttr, defStyleRes)
}

fun getThemeAttributeColorByName(themeAttr: String, context: Context): Int? {
  val resourceId = context.resources.getIdentifier(themeAttr, "color", context.packageName)
  return if (resourceId != 0) ContextCompat.getColor(context, resourceId)
  else null
}

fun getThemeAttributeColor(themeAttrID: Int, context: Context): Int? {
  val outValue = TypedValue()
  val theme = context.theme
  val wasResolved = theme.resolveAttribute(themeAttrID, outValue, true)
  return if(!wasResolved) null
  else if (outValue.resourceId != 0) ContextCompat.getColor(context, outValue.resourceId)
  else outValue.data
}

fun getAttributeColorValue(
    attributeId: Int,
    attributeSet: AttributeSet?,
    context: Context,
    styleable: IntArray? = R.styleable.ProgressButton,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
): Int? {
  val ta = context.obtainStyledAttributes(attributeSet, styleable, defStyleAttr, defStyleRes)
  return try {
    ta.getColor(attributeId, -1).let { if (it != -1) it else null }
  } catch (ignored: Exception) {
    null
  } finally {
    ta.recycle()
  }
}

fun getAttributeStringValue(
    attributeId: Int,
    attributeSet: AttributeSet?,
    context: Context,
    styleable: IntArray? = R.styleable.ProgressButton,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
): String {
  val ta = context.obtainStyledAttributes(attributeSet, styleable, defStyleAttr, defStyleRes)
  return try {
    ta.getString(attributeId)
  } catch (ignored: Exception) {
    ""
  } finally {
    ta.recycle()
  }
}

fun getAttributeFloatValue(
    attributeId: Int,
    attributeSet: AttributeSet?,
    context: Context,
    styleable: IntArray? = R.styleable.ProgressButton,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
): Float? {
  val ta = context.obtainStyledAttributes(attributeSet, styleable, defStyleAttr, defStyleRes)
  return try {
    ta.getDimension(attributeId, -1f).let { if (it != -1f)  it else null }
  } catch (ignored: Exception) {
    null
  } finally {
    ta.recycle()
  }
}

fun getAttributeBooleanValue(
    attributeId: Int,
    attributeSet: AttributeSet?,
    context: Context,
    styleable: IntArray? = R.styleable.ProgressButton,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
): Boolean {
  val ta = context.obtainStyledAttributes(attributeSet, styleable, defStyleAttr, defStyleRes)
  return try {
    ta.getBoolean(attributeId, false)
  } catch (ignored: Exception) {
    false
  } finally {
    ta.recycle()
  }
}
