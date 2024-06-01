package com.shu.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductDbo(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int?,
    @ColumnInfo("category_id")
    val categoryId: Int?,
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("description")
    val description: String?,
    @ColumnInfo("image")
    val image: String?,
    @ColumnInfo("priceCurrent")
    val priceCurrent: Int?,
    @ColumnInfo("price_old")
    val priceOld: String?,
    @ColumnInfo("measure")
    val measure: Int?,
    @ColumnInfo("measure_unit")
    val measureUnit: String?,
    @ColumnInfo("energy")
    val energyPer100Grams: Double?,
    @ColumnInfo("proteins")
    val proteinsPer100Grams: Double?,
    @ColumnInfo("fat")
    val fatsPer100Grams: Double?,
    @ColumnInfo("carbohydrates")
    val carbohydratesPer100Grams: Double?,
    @ColumnInfo("tag_ids")
    val tagIds: ArrayList<String>
)

