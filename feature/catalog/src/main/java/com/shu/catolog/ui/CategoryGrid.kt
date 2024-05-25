package com.shu.catolog.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shu.catolog.R
import com.shu.modules.Category
import com.shu.modules.Product

@Composable
fun CategoryGrid(
    products: List<Product>,
    modifier: Modifier = Modifier,
   // onBookClicked: (Category) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        itemsIndexed(products) { _, product ->
            BooksCard(product = product, modifier)
        }
    }
}



@Composable
fun BooksCard(
    product: Product,
    modifier: Modifier,
    //onBookClicked: (Book) -> Unit
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .requiredHeight(296.dp)
            .clickable { }, //onBookClicked(book) },
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(id = R.drawable.soup),
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
        }
    }
}