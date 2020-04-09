package com.example.simplegithubsearch.ui.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseFragment
import com.example.simplegithubsearch.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {
  override val layoutResId: Int = R.layout.fragment_main

  override fun initViews(savedInstanceState: Bundle?) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun addObserves() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun obtainViewModel(): MainViewModel =
    viewModels<MainViewModel> { viewModelFactory }.value
}