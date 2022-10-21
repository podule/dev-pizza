package com.galia.dev.pizza.menu

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.galia.dev.pizza.R
import com.galia.dev.pizza.databinding.FragmentMenuBinding
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MenuFragment : Fragment(), NavigationBarView.OnItemSelectedListener {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var pagingAdapter: MenuAdapter
    private lateinit var discountAdapter: DiscountAdapter
    private val viewModel: MenuViewModel by viewModels()
    private lateinit var menuHost: MenuHost

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        pagingAdapter = MenuAdapter { id ->
            val direction = MenuFragmentDirections.actionMenuFragmentToPizzaModalSheet(id)
            binding.root.findNavController().navigate(direction)
        }
        discountAdapter = DiscountAdapter()

        val menuDataAdapterModels = listOf(
            MenuDataAdapterModel(
                resources.getString(R.string.menu_discount),
                discountAdapter,
                LinearLayoutManager.HORIZONTAL
            ),
            MenuDataAdapterModel(
                resources.getString(R.string.menu_menu),
                pagingAdapter,
                LinearLayoutManager.VERTICAL
            )
        )
        val adapter = ParentMenuAdapter(menuDataAdapterModels)
        binding.menuList.layoutManager = LinearLayoutManager(requireContext())
        binding.menuList.adapter = adapter

        menuHost = requireActivity()
        addMenu()
        bindMenuData()
        initBottomNavigation()

        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_menu -> { true }
            R.id.menu_cart -> {
                val direction = MenuFragmentDirections.actionMenuFragmentToCartFragment()
                binding.root.findNavController().navigate(direction)
                true
            }
            else -> false
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(this)
        lifecycleScope.launch {
            viewModel.order.collect { orderProto ->
                if (orderProto.id != 0) {
                    val badge = binding.bottomNavigation.getOrCreateBadge(R.id.menu_cart)
                    badge.isVisible = true
                }
            }
        }
    }

    private fun addMenu() {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_sort_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.sorted -> {
                        updateMenu()
                        true
                    }
                    else -> true
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun updateMenu() {
        with(viewModel) {
            toggleSorted()
        }
    }

    private fun bindMenuData() {
        lifecycleScope.launch {
            viewModel.menu.collectLatest { data ->
                pagingAdapter.submitData(data)
            }
        }

        lifecycleScope.launch {
            viewModel.discounts
                .catch { exception ->
                    showToastMessage("Ошибка загрузки данных: ${exception.message}")
                }
                .collectLatest { data -> discountAdapter.submitList(data) }

        }

        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collect { loadState ->
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                    ?: loadState.refresh as? LoadState.Error
                errorState?.let {
                    showToastMessage("Ошибка загрузки данных: ${it.error}")
                }
            }
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(
            activity,
            "Ошибка загрузки данных: $message",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}