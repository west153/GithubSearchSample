package com.example.simplegithubsearch

import com.example.simplegithubsearch.data.baseUrl
import com.example.simplegithubsearch.data.source.RemoteSearchDataSource
import com.example.simplegithubsearch.data.source.SearchDataSource
import com.example.simplegithubsearch.data.source.SearchRepository
import com.example.simplegithubsearch.data.source.SearchRepositoryImpl
import com.example.simplegithubsearch.data.source.remote.ApiManager

object RepositoryLocator {
  @Volatile
  var searchRepository: SearchRepository? = null

  fun provideSearchRepository(): SearchRepository {
    synchronized(this) {
      return searchRepository ?: createSearchRepository()
    }
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