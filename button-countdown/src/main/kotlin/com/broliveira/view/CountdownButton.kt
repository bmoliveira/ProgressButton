package com.broliveira.view

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.broliveira.view.helper.AttributeGetter
import com.broliveira.view.helper.DrawableHelper
import com.broliveira.view.helper.setColor
import com.github.guilhe.circularprogressview.CircularProgressView
import org.jetbrains.anko.*

class CountdownButton(
    context: Context,
    private val attrs: AttributeSet?,
    private val defStyleAttr: Int,
    private val defStyleRes: Int) : FrameLayout(context, attrs), AttributeGetter {

  constructor(context: Context): this(context, null, 0 , 0)
  constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0, 0)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr, 0)

  override val defaultStyleable: IntArray? = R.styleable.CountdownButton

  var defaultCountdownCallback: ((completed: Boolean) -> Unit) = {}

  var isLoading: Boolean = false
    set(value) {
      field = value
      progressView.visibility = if (value) { View.VISIBLE } else { View.INVISIBLE }
      countdownHolder.visibility = if (value) { View.INVISIBLE } else { View.VISIBLE }
    }

  var title: CharSequence = defaultTitle
    set(value) {
      field = value
      titleTextView.text = value
    }

  var titleColor: Int = defaultTitleColor
    set(value) {
      field = value
      titleTextView.textColor = value
      countdownView.setColor(value)
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

  var countDownValue: Int = defaultCountdownSeconds
    set(value) {
      field = value+1
      countdownLabel.text = field.toString()

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

  private val internalMargin: Int = (buttonElevation * 1.5).toInt()

  private lateinit var titleTextView: TextView

  private lateinit var countdownLabel: TextView

  private lateinit var countdownView: CircularProgressView

  private lateinit var countdownHolder: View

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
    }


    frameLayout {
      countdownHolder = frameLayout {
        visibility = View.INVISIBLE

        countdownLabel = textView {
          text = countDownValue.toString()
          textColor = titleColor
        }.lparams {
          textAlignment = View.TEXT_ALIGNMENT_CENTER
          gravity = Gravity.CENTER
        }

        countdownView = CircularProgressView(context)
            .also {
              it.setAnimationInterpolator(LinearInterpolator())
              it.lparams(dip(60), dip(60))
              this.addView(it)
            }
            .apply {
              progressColor = titleColor
              progressStrokeThickness = dip(4).toFloat()
              isProgressThumbEnabled = false
              isShadowEnabled = false
              backgroundColor = ContextCompat.getColor(context, android.R.color.transparent)
            }
      }

      progressView = progressBar {
        setColor(titleColor)
        visibility = View.INVISIBLE
      }.lparams(dip(35), dip(35)){
        gravity = Gravity.CENTER
      }

    }.lparams {
      gravity = Gravity.CENTER
      alignParentRight()
      centerVertically()
    }


  }

  init {
    isClickable = true
    updateValues()
  }

  private fun resetCountdown(shouldCancelTimer: Boolean = false) {
    countdownHolder.visibility = View.INVISIBLE
    countdownView.resetProgress()
    countdownLabel.text = countDownValue.toString()
    if (shouldCancelTimer && countdownTimer != null) {
      countdownTimer?.cancel()
      defaultCountdownCallback(false)
    }
  }

  private var countdownTimer: CountDownTimer? = null

  fun startCountDown(callback: (completed: Boolean) -> Unit = defaultCountdownCallback) {
    resetCountdown(true)
    isLoading = false
    countdownHolder.visibility = View.VISIBLE
    val animationTimeMillis = (1000 * countDownValue).toLong()
    countdownView.setProgress(100f, true, animationTimeMillis)

    countdownTimer = object: CountDownTimer(animationTimeMillis, 1000L) {
      override fun onFinish() {
        callback?.let { it(true) }
        if (countdownTimer == this)
          countdownTimer = null
        resetCountdown()
      }

      override fun onTick(p0: Long) {
        countdownLabel.text = Math.ceil((p0/1000).toDouble()).toInt().toString()
      }
    }.start()
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
    this.countdownLabel.text = this.countDownValue.toString()
    updateBackground()
    this.invalidate()
    this.requestLayout()
  }

  private val defaultBackgroundDrawable: Drawable?
    get() = DrawableHelper.getSelectableDrawableFor(rippleBackgroundColor, rippleSecondaryColor, buttonRadius)

  private val defaultTitle: String
    get() = getAttributeStringValue(R.styleable.CountdownButton_cdbTitle, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)

  private val typefaceName: String
    get() = getAttributeStringValue(R.styleable.CountdownButton_cdbTitleTypeFaceName, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)

  private val defaultTitleTextSize: Float
    get() = getAttributeFloatValue(R.styleable.CountdownButton_cdbTitleTextSize, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)
        ?: context.sp(9).toFloat()

  private val defaultCountdownSeconds: Int
    get() = getAttributeIntValue(R.styleable.CountdownButton_cdbCountdownSeconds, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)
        ?: 5

  private val defaultTitleColor: Int
    get() = getAttributeColorValue(R.styleable.CountdownButton_cdbTitleColor, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)
        ?: getThemeAttributeColor(android.R.attr.textColor, context)
        ?: getThemeAttributeColorByName("textColor", context)
        ?: ContextCompat.getColor(context, android.R.color.white)

  private val defaultRippleBackgroundColor: Int
    get() = getAttributeColorValue(R.styleable.CountdownButton_cdbRippleBackgroundColor, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)
        ?: getThemeAttributeColor(android.R.attr.colorPrimary, context)
        ?: getThemeAttributeColorByName("colorPrimary", context)
        ?: ContextCompat.getColor(context, android.R.color.holo_red_dark)

  private val defaultRippleSecondaryColor: Int
    get() = getAttributeColorValue(R.styleable.CountdownButton_cdbRippleSecondaryColor, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)
        ?: getThemeAttributeColor(android.R.attr.colorPrimaryDark, context)
        ?: getThemeAttributeColorByName("colorPrimaryDark", context)
        ?: ContextCompat.getColor(context, android.R.color.holo_red_light)

  private val defaultButtonRadius: Float
    get() = getAttributeFloatValue(R.styleable.CountdownButton_cdbButtonRadius, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)
        ?: dip(30).toFloat()

  private val defaultMinHeight: Int
    get() = getAttributeFloatValue(R.styleable.CountdownButton_cdbMinHeight, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)?.toInt()
        ?: dip(42)

  private val defaultMaxWidth: Int
    get() = getAttributeFloatValue(R.styleable.CountdownButton_cdbMaxWidth, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)?.toInt()
        ?: Integer.MAX_VALUE

  private val defaultButtonElevation: Float
    get() = getAttributeFloatValue(R.styleable.CountdownButton_cdbButtonElevation, attrs, context, R.styleable.CountdownButton, defStyleAttr, defStyleRes)
        ?: context.dip(4).toFloat()
}
