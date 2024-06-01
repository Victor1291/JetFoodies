package com.shu.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shu.database.models.BasketDbo
import com.shu.database.models.CategoryDbo
import com.shu.database.models.ProductDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodieDao {


    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductDbo>

    @Query("SELECT * FROM basket")
    fun basket(): Flow<List<BasketDbo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProducts(listProducts: List<ProductDbo>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(lisCategories: List<CategoryDbo>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBasket(product: BasketDbo)

    @Query("DELETE FROM basket WHERE id = :id")
    suspend fun delFromBasket(id: Int)

    //count + 1

    //count - 1


}