package com.example.simplegithubsearch.ui.main

import android.os.Bundle
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseActivity
import com.example.simplegithubsearch.databinding.ActivityMainBinding
import com.example.simplegithubsearch.utils.navigateSafe

class MainActivity : BaseActivity<ActivityMainBinding>() {

  override val layoutResId: Int = R.layout.activity_main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(binding?.toolbar)

    binding?.buttonSearch?.setOnClickListener {
      startSearch()
    }
  }

  private fun startSearch(){
    val directions = MainFragmentDirections.actionMainFragmentToSearchFragment()
    navController.navigateSafe(directions)
  }

}
