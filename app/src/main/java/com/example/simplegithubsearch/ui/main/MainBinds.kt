package com.example.simplegithubsearch.ui.main

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:onEditorSearchAction")
fun EditText.onEditorListener(f: (() -> Unit)?) {

  if (f == null)
    this.setOnEditorActionListener(null)
  else
    this.setOnEditorActionListener { _: TextView, action: Int, event: KeyEvent? ->
      val result = when (action) {
        EditorInfo.IME_ACTION_SEARCH -> true
        else -> false
      }

      val keyDown = event?.keyCode == KeyEvent.KEYCODE_SEARCH && event.action == KeyEvent.ACTION_DOWN

      if (result || keyDown) true.apply { f.invoke() } else false
    }
}