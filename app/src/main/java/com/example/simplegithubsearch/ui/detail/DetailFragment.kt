package com.example.simplegithubsearch.ui.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseFragment
import com.example.simplegithubsearch.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
  override val layoutResId: Int = R.layout.fragment_detail

  override fun obtainViewModel(): DetailViewModel =
    viewModels<DetailViewModel> { viewModelFactory }.value

  override fun initViews(savedInstanceState: Bundle?) {
  }

  override fun setObserves() {
  }
}