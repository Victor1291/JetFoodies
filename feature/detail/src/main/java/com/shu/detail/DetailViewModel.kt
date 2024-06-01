package com.shu.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.detail.data.DetailRepository
import com.shu.modules.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
) : ViewModel() {

    val basket = repository.getBasket()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addInCart(product: Product) {
        viewModelScope.launch {
            repository.addProduct(product)
        }
    }

}