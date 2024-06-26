package com.shu.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shu.modules.BasketEntity
import com.shu.modules.Product

@Entity(tableName = "basket")
data class BasketDbo(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int?,
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("count")
    val count: Int?,
    @ColumnInfo("image")
    val image: String?,
    @ColumnInfo("priceCurrent")
    val priceCurrent: Int?,
    @ColumnInfo("price_old")
    val priceOld: String?,
)

fun BasketDbo.fromDb(): BasketEntity {
    return with(this) {
        BasketEntity(
            id = id,
            name = name,
            count = count,
            image = image,
            priceCurrent = priceCurrent,
            priceOld = priceOld,
        )
    }
}


fun Product.toDb(): BasketDbo {
    return with(this) {
        BasketDbo(
            id = id,
            name = name,
            count = 1,
            image = image,
            priceCurrent = priceCurrent,
            priceOld = priceOld,
        )
    }
}