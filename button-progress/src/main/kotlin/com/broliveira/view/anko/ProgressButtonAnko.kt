package com.broliveira.view.anko

import android.view.ViewManager
import com.broliveira.view.ProgressButton
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.progressButton() = progressButton {}
inline fun ViewManager.progressButton(init: ProgressButton.() -> Unit): ProgressButton
    = ankoView( { ProgressButton(it) },0, init)
