package com.galia.dev.pizza.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.data.repositories.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {


    val discounts: Flow<List<Discount>> = repository.getDiscounts()
    private val isSorted = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    var menu: Flow<PagingData<Pizza>> = isSorted.flatMapLatest { flag ->
        repository.getMenuResultStream(flag).cachedIn(viewModelScope)
    }

    val order = repository.getOrderProto()

    fun toggleSorted() {
        val flag = isSorted.value
        isSorted.value = !flag
    }
}