package com.galia.dev.pizza.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.data.repositories.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {

    private var _menu = MutableLiveData<List<Pizza>>()
    val menu: LiveData<List<Pizza>> = _menu

    private val _status = MutableLiveData("Загузка данных")
    val status: LiveData<String> = _status

    init {
        getMenu()
    }

    private fun getMenu() {

        viewModelScope.launch {
            try {
                repository.getMenu().collect {
                    _menu.value = it
                }
                _status.value = ""
            } catch (e: java.lang.Exception) {
                _status.value = "Failure: ${e.message}"
            }

        }
    }
}