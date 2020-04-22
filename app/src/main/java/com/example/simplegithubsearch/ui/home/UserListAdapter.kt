package com.example.simplegithubsearch.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.simplegithubsearch.R
import com.example.simplegithubsearch.data.UserDetail
import com.example.simplegithubsearch.databinding.ItemUserListBinding

class UserListAdapter(
  private val requestManager: RequestManager,
  private val viewModel: HomeViewModel
) : ListAdapter<UserDetail, UserListAdapter.UserListViewHolder>(UserDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
    return UserListViewHolder.create(parent, requestManager)
  }

  override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
    holder.bind(viewModel, getItem(position))
  }

  override fun onViewRecycled(holder: UserListViewHolder) {
    super.onViewRecycled(holder)
    holder.clear()
  }

  class UserListViewHolder private constructor(
    private val binding: ItemUserListBinding,
    private val requestManager: RequestManager
  ) : RecyclerView.ViewHolder(binding.root) {

    private val requestOption = RequestOptions().apply {
      placeholder(R.color.colorBlack)
    }

    fun bind(vm: HomeViewModel, user: UserDetail) {
      binding.tvId.text = user.login
      binding.tvDescription.text = user.bio

      requestManager
        .load(user.avatar_url)
        .apply(requestOption)
        .into(binding.ivProfile)
    }

    fun clear() {
      requestManager.clear(binding.ivProfile)
    }

    companion object {
      fun create(parent: ViewGroup, requestManager: RequestManager): UserListViewHolder {
        val binding =
          ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding, requestManager)
      }
    }
  }

}