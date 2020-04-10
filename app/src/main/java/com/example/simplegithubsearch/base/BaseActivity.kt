package com.example.simplegithubsearch.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {
  protected var binding: V? = null
  protected abstract val layoutResId: Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this,layoutResId)
  }

  override fun onDestroy() {
    super.onDestroy()
    binding?.unbind()
    binding = null
  }

}