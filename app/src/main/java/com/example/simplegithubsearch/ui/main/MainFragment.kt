package com.example.simplegithubsearch.ui.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseFragment
import com.example.simplegithubsearch.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

  companion object {
    fun newInstance(bundle: Bundle) = MainFragment().apply { arguments = bundle }
  }

  override val layoutResId: Int = R.layout.fragment_main

  override fun initViews(savedInstanceState: Bundle?) {
  }

  override fun addObserves() {
  }

  override fun obtainViewModel(): MainViewModel =
    viewModels<MainViewModel> { viewModelFactory }.value
}