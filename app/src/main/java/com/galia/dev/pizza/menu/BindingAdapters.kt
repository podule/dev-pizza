package com.galia.dev.pizza.menu

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.galia.dev.pizza.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        view.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }

}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}