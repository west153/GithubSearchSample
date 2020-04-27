package com.example.simplegithubsearch.utils

import io.reactivex.Flowable
import org.reactivestreams.Publisher

@Suppress("UNCHECKED_CAST")
fun <T, R> Flowable<T>.flatMapZip(mapper: (T) -> Iterable<Publisher<out R>>): Flowable<List<R>> {
  return this.flatMap { source: T ->
    if (source is ArrayList<*> && source.isEmpty())
      Flowable.just(arrayListOf())
    else
      Flowable.zip(mapper(source)) { t: Array<Any> -> t.map { it as R } }
  }
}

@Suppress("UNCHECKED_CAST")
fun <T, R> Flowable<T>.concatMapZip(mapper: (T) -> Iterable<Publisher<out R>>): Flowable<List<R>> {
  return this.concatMap { source: T ->
    if (source is ArrayList<*> && source.isEmpty())
      Flowable.just(arrayListOf())
    else
      Flowable.zip(mapper(source)) { t: Array<Any> -> t.map { it as R } }
  }
}
