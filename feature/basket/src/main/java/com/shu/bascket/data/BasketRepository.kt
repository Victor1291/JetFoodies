package com.shu.bascket.data

import com.shu.modules.CartProduct
import kotlinx.coroutines.flow.Flow


interface BasketRepository {

    fun getBasket(): Flow<List<CartProduct>>

// увеличивать счётчик и менять цену
    // уменьшать счётчик до 1
// удалять нажатием на корзинку.
// очистка всей корзинки.

    suspend fun clearBasket()

}