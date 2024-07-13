package com.shu.catolog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.shu.catolog.ui.ProductView
import com.shu.home.state.ErrorScreen
import com.shu.home.state.LoadingScreen
import com.shu.modules.Product


@Composable
fun CheckState(
    deepLinkData: String,
    viewModel: HomeViewModel = hiltViewModel(),
    onCategoryClick: (Int) -> Unit,
    onProductClick: (Product) -> Unit
) {
    val viewState by viewModel.uiState.collectAsState()


    when (viewState) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> {

            ProductView(
                deepLinkData = deepLinkData,
                stateScreen = (viewState as UiState.Success).stateScreen,
                viewModel = viewModel,
                onCategoryClick = onCategoryClick,
                onProductClick = onProductClick
            )
        }

        is UiState.Error -> ErrorScreen(
            retryAction = { },
        )

    }
}
