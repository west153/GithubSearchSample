package com.example.simplegithubsearch.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseFragment
import com.example.simplegithubsearch.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

  companion object {
    fun newInstance(bundle: Bundle) = HomeFragment().apply { arguments = bundle }
  }

  override val layoutResId: Int = R.layout.fragment_home

  private lateinit var requestManager: RequestManager
  private var listAdapter: UserListAdapter? = null

  override fun obtainViewModel(): HomeViewModel =
    viewModels<HomeViewModel> { viewModelFactory }.value

  override fun initViews(savedInstanceState: Bundle?) {
    requestManager = Glide.with(this)
    binding?.let {
      it.viewModel = viewModel
    }
    setListAdapter()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    requestManager.onDestroy()
  }

  override fun setObserves() {
  }

  private fun setListAdapter() {
    binding?.let {
      listAdapter = UserListAdapter(requestManager, it.viewModel!!)
      it.userList.adapter = listAdapter
    }
  }

}