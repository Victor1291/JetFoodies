package com.shu.network

import com.shu.modules.Category
import com.shu.modules.Product
import com.shu.modules.StateScreen
import com.shu.modules.Tag
import com.shu.network.models.fromApi
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ServiceApi,
) : Repository {
    override suspend fun getAll(): StateScreen {
        return StateScreen(
            category = getCategories(),
            products = getProducts(),
        )
    }

    override suspend fun getProducts(): List<Product> {
        return api.getProducts().map {
            it.fromApi()
        }
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

}