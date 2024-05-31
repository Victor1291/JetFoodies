package com.shu.jetfoodies

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shu.catolog.CheckState
import com.shu.detail.DetailScreen

/*
import com.shu.detail_movie.DetailCheckState
import com.shu.home.CheckState
*/


private const val argumentKey = "arg"

@Composable
fun MainNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
) {
    NavHost(
        navController = navController, startDestination = BottomNavigationScreens.MainScreen.route
    ) {
        composable(BottomNavigationScreens.MainScreen.route) {
            viewModel.changeStateTOpBar(true)

            CheckState(onProductClick = { product ->
                Log.d("navHost", "Click on Card $it")
                val productLink = "${BottomNavigationScreens.DetailScreen.route}/${
                    ProductParametersType.serializeAsValue(product)
                }"
                println("productLink : $productLink")
                navController.navigate(
                    route = productLink
                )
            }, onCategoryClick = {}
                )
        }

        composable(BottomNavigationScreens.SearchScreen.route) {
            /* viewModel.changeStateTOpBar(false)
             viewModel.getAllCity()
             CityScreen(viewModel, onCityClicked = {
                 viewModel.choiceCity = it ?: "Vladivostok"
                 navController.navigate(
                     BottomNavigationScreens.DetailScreen.route
                 )
             })*/
            BackHandler {
                navController.popBackStack()
            }
        }

        composable(BottomNavigationScreens.PersonScreen.route) {
            /* viewModel.changeStateTOpBar(false)
             viewModel.getAllCity()
             CityScreen(viewModel, onCityClicked = {
                 viewModel.choiceCity = it ?: "Vladivostok"
                 navController.navigate(
                     BottomNavigationScreens.DetailScreen.route
                 )
             })*/
            BackHandler {
                navController.popBackStack()
            }
        }
        composable(
            route = "${BottomNavigationScreens.DetailScreen.route}/{$argumentKey}",
            arguments = listOf(navArgument(argumentKey) {
                type = ProductParametersType
            })
        ) { navBackStackEntry ->

            val arguments = navBackStackEntry.arguments
            val params = arguments?.getString(argumentKey)

            val paramsData = params?.let {
                ProductParametersType.parseValue(it)
            }
            DetailScreen(paramsData,navController)

            BackHandler {
                navController.popBackStack()
            }
        }
    }
}
