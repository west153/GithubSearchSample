package com.example.simplegithubsearch.ui.main

import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.MutableLiveData
import com.example.simplegithubsearch.base.BaseViewModel
import com.example.simplegithubsearch.data.source.SearchRepository
import io.reactivex.rxkotlin.addTo

class MainViewModel(private val searchRepository: SearchRepository) : BaseViewModel() {

  val input = MutableLiveData<String>()
  val aa = { getUser() }

  fun getUser() {
    EditorInfo.IME_ACTION_SEARCH
    searchRepository.userSearch(input.value ?: return)
      .doOnSubscribe { input.value = "" }
      .subscribe(
        {
          Log.d("MainViewModel", it.toString())
        }, { it.printStackTrace() })
      .addTo(compositeDisposable)
  }

}