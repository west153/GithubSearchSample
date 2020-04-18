package com.example.simplegithubsearch.ui.home

import androidx.lifecycle.LiveData
import com.example.simplegithubsearch.base.BaseViewModel
import com.example.simplegithubsearch.data.User
import com.example.simplegithubsearch.ui.SearchViewModel

class HomeViewModel(
  private val searchViewModel: SearchViewModel
) : BaseViewModel() {
  private val userList: LiveData<List<User>> get() = searchViewModel.userList
}