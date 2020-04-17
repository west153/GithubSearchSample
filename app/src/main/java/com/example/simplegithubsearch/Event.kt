package com.example.simplegithubsearch

import androidx.lifecycle.Observer

class Event<out T>(private val content: T) {

  var changed = false

  fun isChanged(): T? {
    return if (changed) {
      null
    } else {
      changed = true
      content
    }
  }

  fun peek() = content
}

class EventObserver<T>(private val callback: (T) -> Unit) : Observer<Event<T>> {
  override fun onChanged(t: Event<T>?) {
    t?.isChanged()?.let(callback)
  }
}
