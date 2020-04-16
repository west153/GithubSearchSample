package com.example.simplegithubsearch.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseFragment
import com.example.simplegithubsearch.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

  companion object {
    fun newInstance(bundle: Bundle) = HomeFragment().apply { arguments = bundle }
  }

  override val layoutResId: Int = R.layout.fragment_home

  override fun obtainViewModel(): HomeViewModel =
    viewModels<HomeViewModel> { viewModelFactory }.value

  override fun initViews(savedInstanceState: Bundle?) {
  }

  override fun addObserves() {
  }
}