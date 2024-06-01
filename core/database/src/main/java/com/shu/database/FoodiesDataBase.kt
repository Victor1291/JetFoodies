package com.shu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shu.database.models.BasketDbo
import com.shu.database.models.CategoryDbo
import com.shu.database.models.ProductDbo
import com.shu.database.models.TagsTypeConverter

@Database(
    entities = [
        ProductDbo::class,
        CategoryDbo::class,
        BasketDbo::class,
    ], version = 1
)
@TypeConverters(TagsTypeConverter::class)
abstract class FoodiesDataBase : RoomDatabase() {

    abstract fun foodieDao(): FoodieDao

}

