package com.galia.dev.pizza.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.galia.dev.pizza.api.Pizza
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : ViewModel() {

    private var _menu = MutableLiveData<List<Pizza>>()

    val menu: LiveData<List<Pizza>> = _menu

    init {
        getMenu()
    }

    private fun getMenu() {
        //@todo initial data
    }
}