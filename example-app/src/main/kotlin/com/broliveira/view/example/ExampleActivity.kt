package com.broliveira.view.example

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.broliveira.extensions.addViewId
import com.broliveira.view.CountdownButton
import com.broliveira.view.ProgressButton
import com.broliveira.view.anko.countdownButton
import com.broliveira.view.anko.progressButton
import com.broliveira.view.rx.countdown
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

fun Disposable.addToCompositeDisposable(compositeDisposable: CompositeDisposable) {
  compositeDisposable.add(this)
}

class ExampleActivity : AppCompatActivity() {

  val disposeBag = CompositeDisposable()

  val view: ExampleFragmentUI by lazy { ExampleFragmentUI() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    view.setContentView(this)
  }

  override fun onResume() {
    super.onResume()
    disposeBag.clear()

    view.countDownButton
        .clicks()
        .flatMap { view.countDownButton.countdown() }
        .subscribe { view.countDownButton.isLoading = true }
        .addToCompositeDisposable(disposeBag)
  }
}

class ExampleFragmentUI : AnkoComponent<ExampleActivity> {
  private val autoLoginEmail = "manbaka+mercado@gmail.com"
  private val autoLoginPassword = "123456"

  lateinit var emailInput: EditText
  private lateinit var emailLayout: TextInputLayout

  lateinit var passwordInput: EditText
  private lateinit var passwordLayout: TextInputLayout

  lateinit var submitButton: ProgressButton

  lateinit var countDownButton: CountdownButton

  lateinit var forgotPasswordButton: Button

  lateinit var sloganView: View

  override fun createView(ui: AnkoContext<ExampleActivity>) = with(ui) {
    scrollView {
      val ids = addViewId()
      isFillViewport = true

      relativeLayout {
        submitButton = progressButton {
          addViewId(ids)
          title = "Login"
        }.lparams {
          alignParentTop()
          alignParentLeft()
          alignParentRight()
          rightMargin = dip(24)
          leftMargin = dip(24)
          topMargin = dip(24)
        }

        forgotPasswordButton = button {
          addViewId(ids)
          text = "Recuperar password"
        }.lparams {
          below(submitButton)
          alignParentLeft()
          alignParentRight()
          rightMargin = dip(24)
          leftMargin = dip(24)
        }

        countDownButton = countdownButton {
          title = "Countdown button"
          countDownValue = 5
        }
            .lparams {
              below(forgotPasswordButton)
              alignParentLeft()
              alignParentRight()
              rightMargin = dip(24)
              leftMargin = dip(24)
            }
      }
    }
  }
}