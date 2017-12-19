package com.broliveira.view.example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.broliveira.view.R
import kotlinx.android.synthetic.main.activity_example.*

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
