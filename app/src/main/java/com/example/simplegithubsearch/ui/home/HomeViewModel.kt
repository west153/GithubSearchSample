package com.example.simplegithubsearch.ui.home

import androidx.lifecycle.LiveData
import com.example.simplegithubsearch.Event
import com.example.simplegithubsearch.base.BaseViewModel
import com.example.simplegithubsearch.data.User
import com.example.simplegithubsearch.ui.SearchViewModel
import com.example.simplegithubsearch.utils.Function2
import com.example.simplegithubsearch.utils.combineLatest

class HomeViewModel(
  private val searchViewModel: SearchViewModel
) : BaseViewModel() {

  private var lastEvent: Event<Unit>? = null


  val isLoading = searchViewModel.doOnSubscribe.combineLatest(
    userList, object : Function2<Event<Unit>, List<User>, Boolean> {
      override fun apply(t1: Event<Unit>?, t2: List<User>?): Boolean {
        // 새로운 doOnSubscribe event 발생시 프로그래스 보여주기
        var isShowing = false
        if (lastEvent != t1) {
          lastEvent = t1
          isShowing = true
        }
        return isShowing
      }
    }
  )

  val userList: LiveData<List<User>> get() = searchViewModel.userList
}