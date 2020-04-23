package com.example.simplegithubsearch.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.default(defaultValue: T) = apply { postValue(defaultValue) }

fun <T1, T2, T3, R> LiveData<T1>.combineLatest(
  source2: LiveData<T2>,
  source3: LiveData<T3>,
  combine: Function3<T1, T2, T3, R>
): LiveData<R> {
  val mediator = MediatorLiveData<R>()
  val combineFunc = {
    mediator.value = combine.apply(this.value, source2.value, source3.value)
  }
  mediator.addSource(this) { combineFunc() }
  mediator.addSource(source2) { combineFunc() }
  mediator.addSource(source3) { combineFunc() }
  return mediator
}

interface Function3<T1, T2, T3, R> {
  @Throws(Exception::class)
  fun apply(t1: T1?, t2: T2?, t3: T3?): R
}
