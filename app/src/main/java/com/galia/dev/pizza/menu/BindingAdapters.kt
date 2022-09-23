package com.galia.dev.pizza.menu

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.galia.dev.pizza.api.models.Pizza

@BindingAdapter("menuData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Pizza>?) {
    val adapter = recyclerView.adapter as MenuAdapter
    adapter.submitList(data)
}