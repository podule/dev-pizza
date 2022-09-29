package com.galia.dev.pizza.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.galia.dev.pizza.databinding.ParentMenuRecyclerBinding

class ParentMenuAdapter(private val parents : List<MenuDataAdapterModel>) : RecyclerView.Adapter<ParentMenuAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ParentMenuRecyclerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun getItemCount(): Int = parents.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = parents[position]
        holder.bind(parent)
    }

    inner class ViewHolder(private val binding: ParentMenuRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MenuDataAdapterModel) {
            binding.titleCategory.text = data.title
            binding.recyclerViewCategory.apply {
                layoutManager = LinearLayoutManager(this.context, data.orientation, false)
                adapter = data.adapter
                setRecycledViewPool(viewPool)
            }
            binding.executePendingBindings()
        }
    }
}

data class MenuDataAdapterModel(val title: String, val adapter: RecyclerView.Adapter<*>, @RecyclerView.Orientation val orientation: Int)