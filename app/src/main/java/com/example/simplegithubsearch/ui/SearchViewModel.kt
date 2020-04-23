package com.example.simplegithubsearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simplegithubsearch.Event
import com.example.simplegithubsearch.data.UserDetail
import com.example.simplegithubsearch.data.source.SearchRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SearchViewModel(private val searchRepository: SearchRepository) {
  private val unit get() = Event(Unit)
  private val compositeDisposable = CompositeDisposable()

  private val _doOnSubscribe = MutableLiveData<Event<Unit>>()
  val doOnSubscribe: LiveData<Event<Unit>> get() = _doOnSubscribe

  private val _doOnError = MutableLiveData<Event<Throwable>>()
  val doOnError: LiveData<Event<Throwable>> get() = _doOnError

  private val _userList = MutableLiveData<List<UserDetail>>()
  val userList: LiveData<List<UserDetail>> get() = _userList

  fun getUser(user: String) {
    searchRepository.userSearch(user)
      .doOnSubscribe { _doOnSubscribe.value = unit }
      .subscribe(
        {
          _userList.value = it
        },
        { _doOnError.value = Event(it) }
      )
      .addTo(compositeDisposable)
  }

  fun clear() {
    compositeDisposable.clear()
  }

}