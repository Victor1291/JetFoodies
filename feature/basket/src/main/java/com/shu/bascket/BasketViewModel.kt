package com.shu.bascket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.bascket.data.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: BasketRepository
) : ViewModel() {

    val basket = repository.getBasket()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

fun clearBasket(){
    viewModelScope.launch {
        repository.clearBasket()
    }
}
}