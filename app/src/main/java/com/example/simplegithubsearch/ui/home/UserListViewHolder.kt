package com.example.simplegithubsearch.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.data.UserDetail
import com.example.simplegithubsearch.databinding.ItemUserListBinding

class UserListViewHolder(
  private val view: ItemUserListBinding,
  private val requestManager: RequestManager,
  private val vm: HomeViewModel
) : RecyclerView.ViewHolder(view.root) {

  val requestOption = RequestOptions().apply {
    placeholder(R.color.colorBlack)
  }

  fun bind(user: UserDetail) {
    view.tvId.text = user.login
    view.tvDescription.text = user.bio

    requestManager
      .load(user.avatar_url)
      .apply(requestOption)
      .into(view.ivProfile)
  }

  fun clear() {
    requestManager.clear(view.ivProfile)
  }
}