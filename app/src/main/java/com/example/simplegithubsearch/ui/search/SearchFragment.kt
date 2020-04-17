package com.example.simplegithubsearch.ui.search

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseFragment
import com.example.simplegithubsearch.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
  override val layoutResId: Int = R.layout.fragment_search

  override fun obtainViewModel(): SearchViewModel =
    viewModels<SearchViewModel> { viewModelFactory }.value

  override fun initViews(savedInstanceState: Bundle?) {
  }

  override fun setObserves() {
  }
}