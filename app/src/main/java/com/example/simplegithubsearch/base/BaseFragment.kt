package com.example.simplegithubsearch.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.simplegithubsearch.ViewModelFactory

abstract class BaseFragment<V : ViewDataBinding, VM : ViewModel> : Fragment() {
  protected val viewModelFactory by lazy { getVmFactory() }
  protected var viewModel: VM? = null
  protected var binding: V? = null
  abstract val layoutResId: Int

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
    binding?.lifecycleOwner = viewLifecycleOwner
    viewModel = obtainViewModel()
    return binding?.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    initViews(savedInstanceState)
    addObserves()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding?.unbind()
    binding = null
    viewModel = null
  }

  abstract fun obtainViewModel(): VM
  abstract fun initViews(savedInstanceState: Bundle?)
  abstract fun addObserves()

  private fun getVmFactory(): ViewModelFactory {
    return ViewModelFactory(this)
  }
}