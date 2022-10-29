package com.galia.dev.pizza.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.galia.dev.pizza.cart.compose.CartScreen
import com.google.android.material.composethemeadapter.MdcTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        (activity as AppCompatActivity).supportActionBar?.hide()
        setContent {
            MdcTheme {
                CartScreen(
                    viewModel,
                    onBackClick = {
                        findNavController().navigateUp()
                    },
                )
            }
        }
    }

    private fun navigateToMenu() {
        val direction = CartFragmentDirections.actionCartFragmentToMenuFragment()
        findNavController().navigate(direction)
    }

}
