package com.galia.dev.pizza.pizza

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.galia.dev.pizza.R
import com.galia.dev.pizza.databinding.ModalSheetPizzaBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PizzaModalSheet : BottomSheetDialogFragment() {

    private var _binding: ModalSheetPizzaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PizzaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ModalSheetPizzaBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        lifecycleScope.launch {
            viewModel.pizza
                .catch { exception ->
                    viewModel.setLoad(false)
                    Toast.makeText(activity, exception.message, Toast.LENGTH_LONG).show()
                    findNavController().navigateUp()
                }
                .collect {
                    binding.pizza = it
                    viewModel.setLoad(false)
                }
        }

        binding.pizzaToOrder.setOnClickListener {
            viewModel.setLoad(true)
            lifecycleScope.launch {
                viewModel.addToCart()
                Toast.makeText(activity, requireContext().resources.getText(R.string.add_to_cart), Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}