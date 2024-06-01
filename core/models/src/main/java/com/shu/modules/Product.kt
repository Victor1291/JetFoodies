package com.shu.modules

import android.os.Bundle

data class Product(
    val id: Int?,
    val categoryId: Int?,
    val name: String?,
    val description: String?,
    val image: String?,
    val priceCurrent: Int?,
    val priceOld: String?,
    val measure: Int?,
    val measureUnit: String?,
    val energyPer100Grams: Double?,
    val proteinsPer100Grams: Double?,
    val fatsPer100Grams: Double?,
    val carbohydratesPer100Grams: Double?,
    val tagIds: ArrayList<String>
)


