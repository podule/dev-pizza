package com.galia.dev.pizza.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.data.repositories.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PizzaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: MenuRepository
) : ViewModel() {

    private val pizzaId: Int = savedStateHandle.get<Int>(PIZZA_ID_SAVED_STATE_KEY)!!
    private var _isLoad = MutableLiveData(true)
    val isLoad: LiveData<Boolean> = _isLoad

    val pizza: Flow<Pizza> = repository.getPizza(pizzaId)

    fun setLoad(flag: Boolean) {
        _isLoad.value = flag
    }

    companion object {
        const val PIZZA_ID_SAVED_STATE_KEY = "pizzaId"
    }
}