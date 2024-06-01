package com.shu.network

import com.shu.database.FoodieDao
import com.shu.database.models.fromDb
import com.shu.database.models.toDb
import com.shu.detail.data.DetailRepository
import com.shu.modules.BasketEntity
import com.shu.modules.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dao: FoodieDao
) : DetailRepository {
    override fun getBasket(): Flow<List<BasketEntity>> {
        return dao.basket().map { list ->
            list.map { product ->
                product.fromDb()
            }
        }
    }

    override suspend fun addProduct(product: Product) {
        dao.addBasket(product.toDb())
    }


}