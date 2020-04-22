package com.example.simplegithubsearch.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.simplegithubsearch.data.UserDetail

class UserDiffCallback : DiffUtil.ItemCallback<UserDetail>() {

  override fun areItemsTheSame(oldItem: UserDetail, newItem: UserDetail): Boolean {
    return newItem.login == oldItem.login
  }

  override fun areContentsTheSame(oldItem: UserDetail, newItem: UserDetail): Boolean {
    return newItem == oldItem
  }
}