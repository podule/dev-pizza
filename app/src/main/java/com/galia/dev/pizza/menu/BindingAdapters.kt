package com.galia.dev.pizza.menu

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.galia.dev.pizza.R
import com.galia.dev.pizza.api.models.Pizza

@BindingAdapter("menuData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Pizza>?) {
    val adapter = recyclerView.adapter as MenuAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    view.load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
    }
}