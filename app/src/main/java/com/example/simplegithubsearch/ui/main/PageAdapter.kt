package com.example.simplegithubsearch.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.simplegithubsearch.ui.favorite.FavoriteFragment

class PageAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

  companion object {
    const val PAGE_POSITION = "PAGE_POSITION"
  }

  private val pageSize = 2

  override fun getItemCount(): Int = pageSize

  override fun createFragment(position: Int): Fragment {
    val bundle = Bundle()
    bundle.putInt(PAGE_POSITION, position)

    return when (position) {
      0 -> MainFragment.newInstance(bundle)
      else -> FavoriteFragment.newInstance(bundle)
    }
  }

}