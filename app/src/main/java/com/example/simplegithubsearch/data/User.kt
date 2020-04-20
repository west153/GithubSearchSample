package com.example.simplegithubsearch.data

data class User(val login: String)

data class UserDetail(
  val login: String,
  val html_url: String,
  val avatar_url: String,
  val bio: String
)