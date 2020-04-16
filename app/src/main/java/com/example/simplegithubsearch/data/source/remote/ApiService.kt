package com.example.simplegithubsearch.data.source.remote

import com.example.simplegithubsearch.data.User
import com.example.simplegithubsearch.data.authorization
import com.example.simplegithubsearch.data.search_user
import com.example.simplegithubsearch.data.token
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

  @GET(search_user)
  fun searchUser(
    @Header(authorization) value: String = token,
    @Query("q") user: String
  ): Flowable<List<User>>

  @GET(search_user)
  fun searchUser(
    @Header(authorization) value: String = token,
    @Query("q") user: String,
    @Query("page") page: Int,
    @Query("per_page") per_page: Int
  ): Flowable<List<User>>
}