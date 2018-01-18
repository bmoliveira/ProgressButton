package com.broliveira.view.helper

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue

interface AttributeGetter {

  val defaultStyleable: IntArray?

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
      styleable: IntArray? = defaultStyleable,
      defStyleAttr: Int = 0,
      defStyleRes: Int = 0
  ): Int? {
    val ta = context.obtainStyledAttributes(attributeSet, styleable, defStyleAttr, defStyleRes)
    return try {
      ta.getColor(attributeId, Integer.MIN_VALUE).let { if (it != Integer.MIN_VALUE) it else null }
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
      styleable: IntArray? = defaultStyleable,
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
      styleable: IntArray? = defaultStyleable,
      defStyleAttr: Int = 0,
      defStyleRes: Int = 0
  ): Float? {
    val ta = context.obtainStyledAttributes(attributeSet, styleable, defStyleAttr, defStyleRes)
    return try {
      ta.getDimension(attributeId, Float.MIN_VALUE).let { if (it != Float.MIN_VALUE)  it else null }
    } catch (ignored: Exception) {
      null
    } finally {
      ta.recycle()
    }
  }

  fun getAttributeIntValue(
      attributeId: Int,
      attributeSet: AttributeSet?,
      context: Context,
      styleable: IntArray? = defaultStyleable,
      defStyleAttr: Int = 0,
      defStyleRes: Int = 0
  ): Int? {
    val ta = context.obtainStyledAttributes(attributeSet, styleable, defStyleAttr, defStyleRes)
    return try {
      ta.getInteger(attributeId, Int.MIN_VALUE).let { if (it != Int.MIN_VALUE)  it else null }
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
      styleable: IntArray? = defaultStyleable,
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
}