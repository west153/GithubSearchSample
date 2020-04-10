package com.example.simplegithubsearch.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import com.example.simplegithubsearch.databinding.LayoutTabBinding
import com.google.android.material.tabs.TabLayout

class CustomTabLayout : TabLayout {

  private val tabs = arrayListOf<TabItem>()
  private val tabViews = arrayListOf<LayoutTabBinding>()

  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

  @SuppressLint("InflateParams")
  private fun initView(context: Context) {
    tabViews.clear()
    for ((index, tab) in tabs.indices.withIndex()) {
      val view = LayoutTabBinding.inflate(LayoutInflater.from(context))
      view.tabIcon.setImageResource(tabs[index].iconRes)
      view.tabText.text = tabs[index].text
      tabViews.add(view)
    }
  }


  fun addTabs(tab: List<TabItem>) {
    tabs.addAll(tab)
    initView(context)
  }

  fun getView(position: Int) = tabViews[position]

  data class TabItem(@DrawableRes val iconRes: Int, val text: String)
}