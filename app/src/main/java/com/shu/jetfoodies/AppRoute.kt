package com.shu.jetfoodies

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppRoute(@StringRes val route: Int, @StringRes val label: Int, val icon: ImageVector) {
    data object MainScreen : AppRoute(
        route = R.string.main_screen,
        label = R.string.catalog,
        icon = Icons.Default.Home
    )

    data object SearchScreen : AppRoute(
        route = R.string.location_screen,
        label = R.string.search,
        icon = Icons.Default.Search
    )

    data object PersonScreen : AppRoute(
        route = R.string.shopping_screen,
        label = R.string.shoppingcart,
        icon = Icons.Default.ShoppingCart
    )

    data object DetailScreen : AppRoute(
        route = R.string.detail_screen,
        label = R.string.dish,
        icon = Icons.Default.Menu
    )

}