package com.shu.jetfoodies

import android.content.Intent
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.shu.bascket.BasketScreen
import com.shu.catolog.CheckState
import com.shu.detail.DetailScreen
import com.shu.detail.ProductScreen

private const val argumentKey = "arg"

@Composable
fun MainNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
) {
    // val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = AppRoute.MainScreen.route
    ) {
        composable(
            AppRoute.MainScreen.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = "example://www.compose/dashboard/{name}"
                action = Intent.ACTION_VIEW
            }),
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { navBackStackEntry ->

            val argument = navBackStackEntry.arguments?.getString("name")
            CheckState(
                deepLinkData = argument ?: "",
                onProductClick = { product ->
                    Log.d("navHost", "Click on Card $product")
                    val productLink = "${AppRoute.DetailScreen.route}/${
                        ProductParametersType.serializeAsValue(product)
                    }"
                    println("productLink : $productLink")
                    navController.navigate(
                        route = productLink
                    )
                }, onCategoryClick = {}
            )
        }

        composable(AppRoute.SearchScreen.route) {

            BackHandler {
                navController.popBackStack()
            }
        }

        composable(AppRoute.PersonScreen.route) {

            BasketScreen()

            BackHandler {
                navController.popBackStack()
            }
        }
        composable(
            route = "${AppRoute.DetailScreen.route}/{$argumentKey}",
            arguments = listOf(navArgument(argumentKey) {
                type = ProductParametersType
            })
        ) { navBackStackEntry ->
            val arguments = navBackStackEntry.arguments
            val params = arguments?.getString(argumentKey)

            val paramsData = params?.let {
                ProductParametersType.parseValue(it)
            }
            ProductScreen(product =  paramsData ,navHostController =  navController, onProductClick = { product ->
                //save in Basket
                viewModel.addProduct(product)
            })

            BackHandler {
                navController.popBackStack()
            }
        }

        composable(
            route = AppRoute.DeepScreen.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = "example://developer.compose/detail/{id}"
                action = Intent.ACTION_VIEW
            }),
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { navBackStackEntry ->
            val argument = navBackStackEntry.arguments?.getString("id")
            Log.d("DeepLink", "deeplink id = $argument")
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "This is Settings Screen",
                    modifier = Modifier.testTag("title"),
                    color = Color.Blue
                )
                Text(
                    text = "$argument",
                    modifier = Modifier.testTag("deeplinkArgument"),
                    color = Color.Blue
                )
            }
            BackHandler {
                navController.popBackStack()
            }
        }

    }
}
