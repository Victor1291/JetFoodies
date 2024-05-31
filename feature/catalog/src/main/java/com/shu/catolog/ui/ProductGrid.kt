package com.shu.catolog.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shu.catolog.R
import com.shu.modules.Product
import kotlinx.coroutines.launch

@Composable
fun ProductGrid(
    products: List<Product>,
    modifier: Modifier = Modifier,
    onProductClick: (Product) -> Unit,
) {
    //Scroll to Top when change category
    val gridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()
    val isAtTop by remember {
        derivedStateOf {
            gridState.firstVisibleItemIndex == 0 && gridState.firstVisibleItemScrollOffset == 0
        }
    }
    if (!isAtTop) {
        coroutineScope.launch {
            gridState.animateScrollToItem(0)
        }
    }

    LazyVerticalGrid(
       modifier = Modifier.padding(bottom = 80.dp),
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp),
        state = LazyGridState()
    ) {
        itemsIndexed(products) { _, product ->
            DishesCard(product = product, modifier, true, onProductClick)
        }
    }
}


@Composable
fun DishesCard(
    product: Product,
    modifier: Modifier,
    sendMessageEnabled: Boolean,
    onProductClick: (Product) -> Unit,
) {
    val border = if (!sendMessageEnabled) {
        BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        )
    } else {
        null
    }
    val disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

    val buttonColors = ButtonDefaults.buttonColors(
        disabledContainerColor = Color.Transparent,
        disabledContentColor = disabledContentColor
    )
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .requiredHeight(296.dp)
            .clickable { onProductClick(product) },
    ) {
        Column(horizontalAlignment = Alignment.Start) {

            Image(
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.rectangle),
                contentDescription = "icon for navigation item"
            )
            product.name?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Start,
                    fontStyle = FontStyle.Normal,
                    fontSize = 16.sp,
                    modifier = modifier

                )
            }
            product.measure?.let { measure ->
                Text(
                    text = "$measure ${product.measureUnit}",
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    modifier = modifier,
                )
            }

        }
        product.priceCurrent?.let { price ->
            Button(
                modifier = Modifier.height(36.dp).align(Alignment.CenterHorizontally),
                enabled = sendMessageEnabled,
                onClick = { onProductClick(product) },
                colors = buttonColors,
                border = border,
                contentPadding = PaddingValues(0.dp),
            ) {
                Text(
                    text = (price / 100).toString(),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

    }
}