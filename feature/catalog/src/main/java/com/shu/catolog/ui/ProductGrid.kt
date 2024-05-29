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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shu.catolog.R
import com.shu.modules.Product

@Composable
fun ProductGrid(
    products: List<Product>,
    modifier: Modifier = Modifier,
    onMessageSent: () -> Unit,
    // onBookClicked: (Category) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        itemsIndexed(products) { _, product ->
            DishesCard(product = product, modifier, true, onMessageSent)
        }
    }
}


@Composable
fun DishesCard(
    product: Product,
    modifier: Modifier,
    sendMessageEnabled: Boolean,
    onMessageSent: () -> Unit,
    //onBookClicked: (Book) -> Unit
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
            .clickable { }, //onBookClicked(book) },
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

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
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(top = 4.dp, bottom = 8.dp)
                )
            }
            product.measure?.let { measure ->
                Text(
                    text = "$measure ${product.measureUnit}",
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(top = 4.dp, bottom = 8.dp)
                )
            }
            product.priceCurrent?.let { price ->
                Button(
                    modifier = Modifier.height(36.dp),
                    enabled = sendMessageEnabled,
                    onClick = onMessageSent,
                    colors = buttonColors,
                    border = border,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = price.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}