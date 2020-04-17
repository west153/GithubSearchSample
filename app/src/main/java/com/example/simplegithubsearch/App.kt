package com.example.simplegithubsearch

import android.app.Application
import com.example.simplegithubsearch.data.baseUrl
import com.example.simplegithubsearch.data.source.RemoteSearchDataSource
import com.example.simplegithubsearch.data.source.SearchDataSource
import com.example.simplegithubsearch.data.source.SearchRepository
import com.example.simplegithubsearch.data.source.SearchRepositoryImpl
import com.example.simplegithubsearch.data.source.remote.ApiManager

class App : Application() {
  private var searchRepository: SearchRepository? = null

  fun provideSearchRepository(): SearchRepository {
    return searchRepository ?: run {
      val repository = SearchRepositoryImpl(createRemoteDataSource())
      searchRepository = repository
      repository
    }
  }

  private fun createRemoteDataSource(): SearchDataSource {
    return RemoteSearchDataSource(ApiManager.getService(baseUrl))
  }
}