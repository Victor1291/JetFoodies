package com.shu.network

import com.shu.bascket.data.BasketRepository
import com.shu.database.FoodieDao
import com.shu.database.models.fromDbToCartProduct
import com.shu.modules.CartProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val dao: FoodieDao
) : BasketRepository {
    override fun getBasket(): Flow<List<CartProduct>> {
        return dao.basket().map { list ->
            list.map { product ->
                product.fromDbToCartProduct()
            }
        }
    }

    override suspend fun clearBasket() {
        dao.cleanBasket()
    }
}