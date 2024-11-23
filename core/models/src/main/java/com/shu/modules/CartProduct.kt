package com.shu.modules

import androidx.compose.ui.graphics.Color
import androidx.annotation.DrawableRes

data class CartProduct(
    val id: Int,
    val backgroundColor: Color = Color.White,
    @DrawableRes val imageId: Int,
    val title: String,
    val quantity: Int,
    val price: Int,
) {
    val totalPrice: Int = quantity * price
}
