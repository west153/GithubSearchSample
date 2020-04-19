package com.example.simplegithubsearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.simplegithubsearch.Event
import com.example.simplegithubsearch.base.BaseViewModel
import com.example.simplegithubsearch.ui.SearchViewModel
import com.example.simplegithubsearch.utils.default

class MainViewModel(
  private val searchViewModel: SearchViewModel
) : BaseViewModel() {

  val input = MutableLiveData<String>()

  private val _pageCurrentItem = MutableLiveData<Event<Int>>().default(Event(0))
  val pageCurrentItem: LiveData<Event<Int>> get() = _pageCurrentItem

  val hideKeyboard: LiveData<Event<Unit>>
    get() = searchViewModel.doOnSubscribe.map {
      input.value = ""
      _pageCurrentItem.value = Event(0)
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