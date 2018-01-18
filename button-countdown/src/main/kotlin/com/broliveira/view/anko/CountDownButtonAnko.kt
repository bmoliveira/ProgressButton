package com.broliveira.view.anko

import android.view.ViewManager
import com.broliveira.view.CountdownButton
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.countdownButton(theme: Int) = countdownButton(theme) {}
inline fun ViewManager.countdownButton(theme: Int = 0, init: CountdownButton.() -> Unit): CountdownButton
    = ankoView( { CountdownButton(it) },theme, init)
