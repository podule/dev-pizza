package com.galia.dev.pizza.menu

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.galia.dev.pizza.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    view.load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
    }
}