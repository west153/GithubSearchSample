package com.example.simplegithubsearch.data

data class User(val login: String)

data class UserDetail(
  val login: String,
  val html_url: String,
  val avatar_url: String,
  val bio: String
) {

  override fun hashCode(): Int {
    return 31 * (login.hashCode() + html_url.hashCode() + avatar_url.hashCode() + bio.hashCode())
  }

  override fun equals(other: Any?): Boolean {
    return (other as? UserDetail)?.let {
      it.login == this.login
          && it.avatar_url == this.avatar_url
          && it.bio == this.bio
          && it.html_url == this.html_url
    } ?: return super.equals(other)
  }
}