package com.example.simplegithubsearch.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.simplegithubsearch.data.UserDetail
import com.example.simplegithubsearch.databinding.ItemUserListBinding

class UserListAdapter(
  private val requestManager: RequestManager,
  private val viewModel: HomeViewModel
) : RecyclerView.Adapter<UserListViewHolder>() {

  private val userList = arrayListOf<UserDetail>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
    val view = ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return UserListViewHolder(view, requestManager, viewModel)
  }


  override fun getItemCount(): Int = userList.size

  override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
    holder.bind(userList[position])
  }

  override fun onViewRecycled(holder: UserListViewHolder) {
    super.onViewRecycled(holder)
    holder.clear()
  }

  fun initList(item: List<UserDetail>) {
    userList.clear()
    userList.addAll(item)
    notifyDataSetChanged()
  }

}