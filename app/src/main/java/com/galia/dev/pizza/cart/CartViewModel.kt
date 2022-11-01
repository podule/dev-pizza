package com.galia.dev.pizza.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galia.dev.pizza.api.models.OrderedPizza
import com.galia.dev.pizza.data.repositories.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    private val _orderedPizza = mutableStateListOf<OrderedPizza>()
    val orderedPizza: List<OrderedPizza>
        get() = _orderedPizza

    private var _flagCreatedOrder = mutableStateOf<Boolean>(false)
    val flagCreatedOrder: Boolean
        get() = _flagCreatedOrder.value

    init {
        viewModelScope.launch {
            _flagCreatedOrder.value = repository.isOrderCreated()
            if (_flagCreatedOrder.value) {
                repository.getOrderedPizza()
                    .catch {

                    }
                    .collect { list ->
                        _orderedPizza += list.toMutableStateList()
                    }
            }
        }
    }
}