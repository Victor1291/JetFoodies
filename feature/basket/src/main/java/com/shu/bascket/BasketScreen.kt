package com.shu.bascket

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BasketScreen(
    viewModel: BasketViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {

    val basket by viewModel.basket.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
            .padding(
                top = 60.dp,
                bottom = 60.dp
            )
    ) {

        items(basket.size) { num ->
            ProductCard(basket[num])
        }
    }
}
