package com.galia.dev.pizza.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.databinding.ListItemMenuBinding

class MenuAdapter(
    val handlerClick: (id: Int) -> Unit
): PagingDataAdapter<Pizza, MenuAdapter.MenuItemHolder>(DiffCallback) {

    class MenuItemHolder(private val binding: ListItemMenuBinding): ViewHolder(binding.root) {
        fun bind(pizza: Pizza, action: (id: Int) -> Unit) {
            binding.pizza = pizza
            binding.menuItem.setOnClickListener {
                action(pizza.id.toInt())
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Pizza>() {
        override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
            return oldItem.url == newItem.url && oldItem.title == newItem.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder {
        return MenuItemHolder(ListItemMenuBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) {
        val pizza = getItem(position)
        pizza?.let {
            holder.bind(pizza){ id ->
                handlerClick(id)
            }
        }
    }

}