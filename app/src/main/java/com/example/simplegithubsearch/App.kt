package com.example.simplegithubsearch

import android.app.Application
import com.example.simplegithubsearch.data.baseUrl
import com.example.simplegithubsearch.data.source.RemoteSearchDataSource
import com.example.simplegithubsearch.data.source.SearchDataSource
import com.example.simplegithubsearch.data.source.SearchRepository
import com.example.simplegithubsearch.data.source.SearchRepositoryImpl
import com.example.simplegithubsearch.data.source.remote.ApiManager
import com.example.simplegithubsearch.ui.SearchViewModel

class App : Application() {
  val searchViewModel: SearchViewModel by lazy { SearchViewModel(searchRepository!!) }
  val searchRepository: SearchRepository by lazy { SearchRepositoryImpl(createRemoteDataSource()) }
   
  private fun createRemoteDataSource(): SearchDataSource {
    return RemoteSearchDataSource(ApiManager.getService(baseUrl))
  }
}