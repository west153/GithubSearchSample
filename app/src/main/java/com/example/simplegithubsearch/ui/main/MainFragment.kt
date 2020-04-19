package com.example.simplegithubsearch.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import com.example.simplegithubsearch.EventObserver
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.base.BaseFragment
import com.example.simplegithubsearch.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

  override val layoutResId: Int = R.layout.fragment_main

  override fun initViews(savedInstanceState: Bundle?) {
    binding?.also {
      it.viewModel = viewModel
      it.pager.adapter = PageAdapter(childFragmentManager, lifecycle)
      TabLayoutMediator(it.tab, it.pager) { tab, position ->
        tab.setText(if (position == 0) R.string.tab_home else R.string.tab_favorite)
      }.attach()
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding?.also {
      it.pager.adapter = null
    }
  }

  override fun setObserves() {
    viewModel?.hideKeyboard?.observe(viewLifecycleOwner, EventObserver {
      hideKeyboard()
    })

    viewModel?.pageCurrentItem?.observe(viewLifecycleOwner, EventObserver {
      setPageCurrentItem(it)
    })
  }

  override fun obtainViewModel(): MainViewModel =
    activityViewModels<MainViewModel> { viewModelFactory }.value

  private fun hideKeyboard() {
    (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).also {
      it.hideSoftInputFromWindow(binding?.input?.windowToken, 0)
    }
  }

  private fun setPageCurrentItem(index: Int) {
    if (binding?.pager?.currentItem != index) {
      binding?.pager?.currentItem = index
    }
  }


}