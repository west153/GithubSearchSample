package com.example.simplegithubsearch

import com.example.simplegithubsearch.data.baseUrl
import com.example.simplegithubsearch.data.source.RemoteSearchDataSource
import com.example.simplegithubsearch.data.source.SearchDataSource
import com.example.simplegithubsearch.data.source.SearchRepository
import com.example.simplegithubsearch.data.source.SearchRepositoryImpl
import com.example.simplegithubsearch.data.source.remote.ApiManager
import com.example.simplegithubsearch.ui.SearchViewModel

object RepositoryLocator {
  @Volatile
  var searchRepository: SearchRepository? = null

  @Volatile
  var searchViewModel: SearchViewModel? = null

  fun provideSearchViewModel(repository: SearchRepository): SearchViewModel {
    synchronized(this) {
      return searchViewModel ?: createSearchViewModel(repository)
    }
  }

  fun provideSearchRepository(): SearchRepository {
    synchronized(this) {
      return searchRepository ?: createSearchRepository()
    }
  }

  private fun createSearchViewModel(repository: SearchRepository): SearchViewModel {
    val viewModel = SearchViewModel(repository)
    searchViewModel = viewModel
    return viewModel
  }

  private fun createSearchRepository(): SearchRepository {
    val repository = SearchRepositoryImpl(createRemoteDataSource())
    searchRepository = repository
    return repository
  }

  private fun createRemoteDataSource(): SearchDataSource {
    return RemoteSearchDataSource(ApiManager.getService(baseUrl))
  }
}