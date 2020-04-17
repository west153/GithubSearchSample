package com.example.simplegithubsearch.ui.favorite

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseFragment
import com.example.simplegithubsearch.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

  companion object {
    fun newInstance(bundle: Bundle) = FavoriteFragment().apply { arguments = bundle }
  }

  override val layoutResId: Int = R.layout.fragment_favorite

  override fun initViews(savedInstanceState: Bundle?) {
  }

  override fun setObserves() {
  }

  override fun obtainViewModel(): FavoriteViewModel =
    viewModels<FavoriteViewModel> { viewModelFactory }.value
}