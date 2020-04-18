package com.example.simplegithubsearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.simplegithubsearch.Event
import com.example.simplegithubsearch.base.BaseViewModel
import com.example.simplegithubsearch.ui.SearchViewModel

class MainViewModel(
  private val searchViewModel: SearchViewModel
) : BaseViewModel() {

  val input = MutableLiveData<String>()

  val hideKeyboard: LiveData<Event<Unit>> get() = searchViewModel.doOnSubscribe.map {
      input.value = ""
      it
    }

  fun getUser() {
    searchViewModel.getUser(input.value ?: return)
  }

  override fun onCleared() {
    super.onCleared()
    searchViewModel.clear()
  }

}