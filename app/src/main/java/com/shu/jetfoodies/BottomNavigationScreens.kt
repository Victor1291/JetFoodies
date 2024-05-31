package com.shu.jetfoodies

import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import com.google.gson.Gson
import com.shu.modules.Product

sealed class BottomNavigationScreens(val route: String, val label: String, val icon: ImageVector) {
    data object MainScreen : BottomNavigationScreens(
        route = "main_screen",
        label = "Home",
        icon = Icons.Default.Home
    )

    data object SearchScreen : BottomNavigationScreens(
        route = "location_screen",
        label = "Поиск",
        icon = Icons.Default.Search
    )

    data object PersonScreen : BottomNavigationScreens(
        route = "location_screen",
        label = "Профиль",
        icon = Icons.Default.Person
    )

    data object DetailScreen : BottomNavigationScreens(
        route = "detail_screen",
        label = "Movie",
        icon = Icons.Default.Menu
    )

}

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