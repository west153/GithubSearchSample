package com.example.simplegithubsearch.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseFragment
import com.example.simplegithubsearch.databinding.FragmentHomeBinding
import com.example.simplegithubsearch.ui.main.MainViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

  companion object {
    fun newInstance(bundle: Bundle) = HomeFragment().apply { arguments = bundle }
  }

  override val layoutResId: Int = R.layout.fragment_home

  private lateinit var requestManager: RequestManager
  private var listAdapter: UserListAdapter? = null

  override fun obtainViewModel(): MainViewModel =
    activityViewModels<MainViewModel> { viewModelFactory }.value

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