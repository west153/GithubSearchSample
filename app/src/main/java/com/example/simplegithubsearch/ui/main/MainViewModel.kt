package com.example.simplegithubsearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplegithubsearch.Event
import com.example.simplegithubsearch.base.BaseViewModel
import com.example.simplegithubsearch.data.UserDetail
import com.example.simplegithubsearch.data.source.SearchRepository
import com.example.simplegithubsearch.utils.default
import io.reactivex.rxkotlin.addTo

class MainViewModel(
  private val searchRepository: SearchRepository
) : BaseViewModel() {

  val input = MutableLiveData<String>()

  private val _pageCurrentItem = MutableLiveData<Event<Int>>().default(Event(0))
  val pageCurrentItem: LiveData<Event<Int>> get() = _pageCurrentItem

  private val _hideKeyboard = MutableLiveData<Event<Unit>>()
  val hideKeyboard: LiveData<Event<Unit>> get() = _hideKeyboard

  private val _isLoading = MutableLiveData<Boolean>()
  val isLoading: LiveData<Boolean> get() = _isLoading

  private val _isUserEmpty = MutableLiveData<Boolean>()
  val isUserEmpty: LiveData<Boolean> get() = _isUserEmpty

  private val _userList = MutableLiveData<List<UserDetail>>()
  val userList: LiveData<List<UserDetail>> get() = _userList

  fun getUser() {
    searchRepository.userSearch(input.value ?: return)
      .doOnSubscribe {
        loadUser(true)
        input.value = ""
        _pageCurrentItem.value = Event(0)
        _hideKeyboard.value = unit
      }
      .subscribe(
        {
          loadUser(false)
          _isUserEmpty.value = it.isEmpty()
          _userList.value = it
        },
        {
          loadUser(false)
          it.printStackTrace()
        }
      )
      .addTo(compositeDisposable)
  }


  private fun loadUser(load: Boolean) {
    _isLoading.value = load
  }

}