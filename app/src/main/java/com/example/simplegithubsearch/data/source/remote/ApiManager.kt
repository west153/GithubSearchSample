package com.example.simplegithubsearch.data.source.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {
  private fun getInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  private fun getHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(60, TimeUnit.SECONDS)
      .addInterceptor(getInterceptor())
      .build()
  }

  private fun gsonConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create(GsonBuilder().setLenient().create())
  }

  fun getService(baseUrl: String): ApiService {
    return Retrofit.Builder()
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(gsonConverterFactory())
      .baseUrl(baseUrl)
      .client(getHttpClient())
      .build()
      .create(ApiService::class.java)
  }
}