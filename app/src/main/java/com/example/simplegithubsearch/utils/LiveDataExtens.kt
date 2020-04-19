package com.example.simplegithubsearch.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.default(defaultValue: T) = apply { postValue(defaultValue) }

fun <T1, T2, R> LiveData<T1>.combineLatest(
  source2: LiveData<T2>,
  combine: Function2<T1, T2, R>
): LiveData<R> {
  val mediator = MediatorLiveData<R>()
  val combineFunc = {
    mediator.value = combine.apply(this.value, source2.value)
  }
  mediator.addSource(this) { combineFunc() }
  mediator.addSource(source2) { combineFunc() }
  return mediator
}


interface Function2<T1, T2, R> {
  @Throws(Exception::class)
  fun apply(t1: T1?, t2: T2?): R
}

interface Function3<T1, T2, T3, R> {
  @Throws(Exception::class)
  fun apply(t1: T1?, t2: T2?, t3: T3?): R
}

interface Function4<T1, T2, T3, T4, R> {
  @Throws(Exception::class)
  fun apply(t1: T1?, t2: T2?, t3: T3?, t4: T4?): R
}