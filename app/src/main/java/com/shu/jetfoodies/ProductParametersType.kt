package com.shu.jetfoodies

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.shu.modules.Product


val ProductParametersType: NavType<Product?> = object : NavType<Product?>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): Product? {
        return  bundle.getString(key)?.let { parseValue(it) }
    }
    override fun parseValue(value: String): Product {
        return Gson().fromJson(value, Product::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: Product?) {
        bundle.putString(key, Gson().toJson(value))
    }
    override fun serializeAsValue(value: Product?): String {
        return Gson().toJson(value)
    }
}