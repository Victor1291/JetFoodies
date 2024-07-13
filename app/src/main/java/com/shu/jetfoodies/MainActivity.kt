package com.shu.jetfoodies

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.shu.jetfoodies.ui.theme.JetFoodiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetFoodiesTheme {
                val navController = rememberNavController()
                val viewModel: MainViewModel = hiltViewModel()
                val bottomNavigationItems = listOf(
                    AppRoute.MainScreen,
                    AppRoute.SearchScreen,
                    AppRoute.PersonScreen,
                )
                Scaffold(
                    content = { MainNavHost(navController, it, viewModel) },
                    bottomBar = {
                        BottomNav(navController, bottomNavigationItems)
                    }
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}
