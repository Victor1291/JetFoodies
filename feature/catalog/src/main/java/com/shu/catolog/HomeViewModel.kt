package com.shu.catolog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.modules.Product
import com.shu.modules.StateScreen
import com.shu.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UiState {
    data class Success(val stateScreen: StateScreen) : UiState
    data object Error : UiState
    data object Loading : UiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _selectCategory = MutableStateFlow(0)
    val selectCategory = _selectCategory.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var allProducts : List<Product> = emptyList()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            _uiState.value =
                try {
                    val request = repository.getAll()
                    allProducts = request.products
                    //Filter on start
                    request.products = allProducts.filter { product ->
                        product.categoryId == request.category[0].id
                    }
                    UiState.Success(request)
                } catch (e: Exception) {
                    Log.e("viewmodelError", "Error $e")
                    UiState.Error
                }
        }
    }

    fun changeSelect(category: Int) {
        _selectCategory.value = category
        val state = uiState.value
        if (state is UiState.Success) {
            val categories = state.stateScreen.category
            _uiState.value = UiState.Success(
                stateScreen = StateScreen(
                    category = categories,
                    products =allProducts.filter { product ->
                        product.categoryId == categories[category].id
                    }),
            )
        }
    }
}
