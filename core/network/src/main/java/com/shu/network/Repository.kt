package com.shu.network

import com.shu.modules.Category
import com.shu.modules.Product
import com.shu.modules.Tag


interface Repository {

    suspend fun getProducts(): List<Product>

    suspend fun getCategories(): List<Category>

    suspend fun getTags(): List<Tag>

}