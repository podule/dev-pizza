package com.galia.dev.pizza.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.api.models.Discount
import com.galia.dev.pizza.data.repositories.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {

    var menu: Flow<PagingData<Pizza>> = repository.getMenuResultStream().cachedIn(viewModelScope)

    val discounts: Flow<List<Discount>> = repository.getDiscounts()
}