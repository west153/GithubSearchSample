package com.example.simplegithubsearch

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.simplegithubsearch.ui.favorite.FavoriteViewModel
import com.example.simplegithubsearch.ui.home.HomeViewModel
import com.example.simplegithubsearch.ui.main.MainViewModel
import com.example.simplegithubsearch.ui.search.SearchViewModel

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
      isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel()
      isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel()
      isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel()
      else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
  } as T

}