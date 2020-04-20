package com.example.simplegithubsearch.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplegithubsearch.data.UserDetail

@BindingAdapter(value = ["app:item"])
fun RecyclerView.setItems(item: List<UserDetail>?) {
  item?.let {
    (this.adapter as? UserListAdapter)?.initList(it)
  }
}