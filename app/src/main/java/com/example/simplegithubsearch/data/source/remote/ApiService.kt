package com.example.simplegithubsearch.data.source.remote

import com.example.simplegithubsearch.data.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

  @GET(SEARCH_USER)
  fun searchUser(
    @Header(AUTHORIZATION) value: String = TOKEN,
    @Query("q") user: String
  ): Flowable<Search<List<User>>>

  @GET(SEARCH_USER)
  fun searchUser(
    @Header(AUTHORIZATION) value: String = TOKEN,
    @Query("q") user: String,
    @Query("page") page: Int,
    @Query("per_page") per_page: Int
  ): Flowable<Search<List<User>>>

  @GET(USER_DETAIL)
  fun userDetail(
    @Header(AUTHORIZATION) value: String = TOKEN,
    @Path("user") login: String
  ): Flowable<UserDetail>
}