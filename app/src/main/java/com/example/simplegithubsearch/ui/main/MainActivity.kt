package com.example.simplegithubsearch.ui.main

import android.os.Bundle
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseActivity
import com.example.simplegithubsearch.component.CustomTabLayout
import com.example.simplegithubsearch.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity<ActivityMainBinding>() {
  private val pageAdapter by lazy { PageAdapter(supportFragmentManager, lifecycle) }
  override val layoutResId: Int = R.layout.activity_main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(binding?.toolbar)

    val tab1 = CustomTabLayout.TabItem(0, getString(R.string.tab_home))
    val tab2 = CustomTabLayout.TabItem(0, getString(R.string.tab_favorite))
    val tabs = arrayListOf(tab1, tab2)
    binding?.tab?.addTabs(tabs)
    binding?.pager?.adapter = pageAdapter

    if (binding?.tab != null && binding?.pager != null) {
      TabLayoutMediator(binding?.tab as TabLayout, binding?.pager!!) { tab, position ->
        tab.customView = binding?.tab?.getView(position)?.root
      }.attach()
    }
  }

}
