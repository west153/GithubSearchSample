package com.example.simplegithubsearch

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.simplegithubsearch.ui.main.MainViewModel

class ViewModelFactory(owner: SavedStateRegistryOwner, args: Bundle? = null) :
  AbstractSavedStateViewModelFactory(owner, args) {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(
    key: String,
    modelClass: Class<T>,
    handle: SavedStateHandle
  ): T = with(modelClass) {
    when {
      isAssignableFrom(MainViewModel::class.java) -> MainViewModel()
      else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
  } as T

}