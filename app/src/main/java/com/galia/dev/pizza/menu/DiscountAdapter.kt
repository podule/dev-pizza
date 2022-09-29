package com.galia.dev.pizza.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.databinding.ListItemDiscountBinding

class DiscountAdapter: ListAdapter<Discount, DiscountAdapter.DiscountItemHolder>(DiffCallback) {

    class DiscountItemHolder(private val binding: ListItemDiscountBinding): ViewHolder(binding.root) {
        fun bind(discount: Discount) {
            binding.discount = discount
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Discount>() {
        override fun areItemsTheSame(oldItem: Discount, newItem: Discount): Boolean {
            return oldItem.img == newItem.img
        }

        override fun areContentsTheSame(oldItem: Discount, newItem: Discount): Boolean {
            return oldItem.img == newItem.img
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountItemHolder {
        return DiscountItemHolder(ListItemDiscountBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DiscountItemHolder, position: Int) {
        val discount = getItem(position)
        discount?.let {
            holder.bind(discount)
        }
    }

}