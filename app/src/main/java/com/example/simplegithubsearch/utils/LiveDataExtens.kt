package com.example.simplegithubsearch.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.default(defaultValue: T) = apply { postValue(defaultValue) }