package com.example.simplegithubsearch.data.source.remote

import com.example.simplegithubsearch.data.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

  @GET(search_user)
  fun searchUser(
    @Header(authorization) value: String = token,
    @Query("q") user: String
  ): Flowable<Search<List<User>>>

  @GET(search_user)
  fun searchUser(
    @Header(authorization) value: String = token,
    @Query("q") user: String,
    @Query("page") page: Int,
    @Query("per_page") per_page: Int
  ): Flowable<Search<List<User>>>

  @GET(user_detail)
  fun userDetail(
    @Header(authorization) value: String = token,
    @Path("user") login: String
  ): Flowable<UserDetail>
}