package com.shu.bascket.data

import com.shu.modules.BasketEntity
import com.shu.modules.Category
import com.shu.modules.Product
import com.shu.modules.StateScreen
import com.shu.modules.Tag
import kotlinx.coroutines.flow.Flow


interface BasketRepository {

     fun getBasket(): Flow<List<BasketEntity>>


}