package com.example.simplegithubsearch.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.simplegithubsearch.Event
import com.example.simplegithubsearch.base.BaseViewModel
import com.example.simplegithubsearch.data.UserDetail
import com.example.simplegithubsearch.ui.SearchViewModel

class HomeViewModel(
  private val searchViewModel: SearchViewModel
) : BaseViewModel() {

  val userList: LiveData<List<UserDetail>>
    get() = searchViewModel.userList.map {
      loadUser(false)
      it
    }

  val doOnSubscribe: LiveData<Event<Unit>> get() = searchViewModel.doOnSubscribe.map {
    it
  }
  val doOnError: LiveData<Event<Throwable>> get() = searchViewModel.doOnError

  private val _isLoading = MutableLiveData<Boolean>()
  val isLoading: LiveData<Boolean> get() = _isLoading

  fun onError(e: Throwable) {
    e.printStackTrace()
    loadUser(false)
  }

  fun loadUser(load: Boolean) {
    _isLoading.value = load
  }

}