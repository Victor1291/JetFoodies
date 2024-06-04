package com.shu.jetfoodies

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNav(navController: NavHostController, items: List<AppRoute>) {
    BottomAppBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            val routeScreen = stringResource(id = screen.route)
            NavigationBarItem(
                selected = currentRoute == routeScreen,
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = stringResource(R.string.icon_for_navigation_item)
                    )
                },
                label = { Text(text = stringResource(id = screen.label)) },
                onClick = {
                    if (currentRoute != routeScreen) {
                        navController.navigate(routeScreen)
                    }
                }
            )
        }
    }
}
