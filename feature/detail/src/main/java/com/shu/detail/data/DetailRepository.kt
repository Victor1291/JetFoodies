package com.shu.detail.data

import com.shu.modules.BasketEntity
import com.shu.modules.Product
import kotlinx.coroutines.flow.Flow


interface DetailRepository {

     fun getBasket(): Flow<List<BasketEntity>>

     suspend fun addProduct(product: Product)

}