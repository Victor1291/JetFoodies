package com.shu.jetfoodies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.shu.jetfoodies.ui.theme.JetFoodiesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetFoodiesTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val viewModel: MainViewModel = hiltViewModel()
                val stateTopBar by viewModel.stateTOpBar.collectAsState()
                val bottomNavigationItems = listOf(
                    BottomNavigationScreens.MainScreen,
                    BottomNavigationScreens.SearchScreen,
                    BottomNavigationScreens.PersonScreen,
                )
                Scaffold(
                    topBar = {
                        if (stateTopBar) {
                            TopBar(
                                header = "CinemaWorld",
                            )
                        } else {
                            TopBar(
                                header = "",
                                leftIconImageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                onLeftIconClick = { navController.navigateUp() },
                            )
                        }
                    },
                    content = { MainNavHost(navController, it, viewModel) },
                    bottomBar = {
                        BottomNav(navController, bottomNavigationItems)
                    }
                )
            }
        }
    }
}
