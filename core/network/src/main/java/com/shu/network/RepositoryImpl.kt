package com.shu.network

import com.shu.catolog.data.Repository
import com.shu.database.FoodieDao
import com.shu.database.models.ProductDbo
import com.shu.database.models.toDb
import com.shu.modules.Category
import com.shu.modules.Product
import com.shu.modules.StateScreen
import com.shu.modules.Tag
import com.shu.network.models.fromApi
import com.shu.network.models.fromDb
import com.shu.network.models.toDb
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ServiceApi,
    private val dao: FoodieDao
) : Repository {
    override suspend fun getAll(): StateScreen {
        val listFromDb = dao.getAllProducts()
       val listProducts = if(listFromDb.isNotEmpty()) {
           listFromDb.map {
               it.fromDb()
           }
       }
       else getProducts()

        return StateScreen(
            category = getCategories(),
            products = listProducts,
        )
    }

    override suspend fun getProducts(): List<Product> {
        val listDb = mutableListOf<ProductDbo>()
        val listApi = api.getProducts().map {
            listDb.add(it.toDb())
            it.fromApi()
        }
        dao.insertProducts(listDb)
        return listApi
    }

    override suspend fun getCategories(): List<Category> {
        return api.getCategories().map {
            it.fromApi()
        }
    }

    override suspend fun getTags(): List<Tag> {
        return api.getTags().map {
            it.fromApi()
        }
    }

    override suspend fun addProduct(product: Product) {
        dao.addBasket(product.toDb())
    }

}