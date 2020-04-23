package com.example.simplegithubsearch

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.simplegithubsearch.data.source.SearchRepository
import com.example.simplegithubsearch.ui.detail.DetailViewModel
import com.example.simplegithubsearch.ui.favorite.FavoriteViewModel
import com.example.simplegithubsearch.ui.home.HomeViewModel
import com.example.simplegithubsearch.ui.main.MainViewModel

class ViewModelFactory(
  private val searchRepository: SearchRepository,
  owner: SavedStateRegistryOwner,
  args: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, args) {

  private val searchViewModel = RepositoryLocator.provideSearchViewModel(searchRepository)

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(
    key: String,
    modelClass: Class<T>,
    handle: SavedStateHandle
  ): T = with(modelClass) {
    when {
      isAssignableFrom(MainViewModel::class.java) -> MainViewModel(searchViewModel)
      isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel()
      isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel()
      isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(searchViewModel)
      else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
  } as T

}