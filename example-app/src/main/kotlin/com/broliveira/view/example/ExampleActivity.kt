package com.broliveira.view.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_example.*
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout

class ExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_example)
  }

  override fun onResume() {
    super.onResume()
    val drawable = mainButton.background
    Log.e("AAA", "$drawable")
  }
}

class ExampleFragmentUI : AnkoComponent<ExampleActivity> {

  override fun createView(ui: AnkoContext<ExampleActivity>) = with(ui) {
    linearLayout()
  }
}