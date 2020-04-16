package com.example.simplegithubsearch.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.simplegithubsearch.R

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {
  protected var binding: V? = null
  protected lateinit var navController: NavController
  protected abstract val layoutResId: Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, layoutResId)
    navController = findNavController(R.id.fragment_nav_host)
  }

  override fun onDestroy() {
    super.onDestroy()
    binding?.unbind()
    binding = null
  }

}