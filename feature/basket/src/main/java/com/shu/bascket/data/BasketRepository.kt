package com.shu.bascket.data

import com.shu.modules.BasketEntity
import kotlinx.coroutines.flow.Flow


interface BasketRepository {

    fun getBasket(): Flow<List<BasketEntity>>

// увеличивать счётчик и менять цену
    // уменьшать счётчик до 1
// удалять нажатием на корзинку.
// очистка всей корзинки.

    suspend fun clearBasket()

}