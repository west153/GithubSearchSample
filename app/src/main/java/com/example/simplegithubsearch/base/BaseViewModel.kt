package com.example.simplegithubsearch.base

import androidx.lifecycle.ViewModel
import com.example.simplegithubsearch.Event
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

  protected val unit get() = Event(Unit)
  protected val compositeDisposable = CompositeDisposable()

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}