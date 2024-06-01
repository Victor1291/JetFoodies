package com.shu.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shu.modules.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    product: Product?,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    onProductClick: (Product) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.background(Color.Transparent)
            )
        },
        content = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)

            DetailView(product = product,onProductClick = onProductClick)
        }
    )
}

@Composable
fun DetailView(
    product: Product?,
    modifier: Modifier = Modifier,
    onProductClick: (Product) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(4.dp),
        modifier = modifier.padding(bottom = 120.dp),
    ) {
        item {
            Image(
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.soup_max),
                contentDescription = "icon for navigation item"
            )
        }
        item {
            Text(
                text = product?.name ?: "",
                lineHeight = 30.sp,
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                text = product?.description ?: "",
                lineHeight = 20.sp,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
        }
        item {
            RowText("Вес", " ${product?.measure} ${product?.measureUnit} ")
            DividerText()
            RowText("Энерг.ценность", " ${product?.energyPer100Grams} kkal ")
            DividerText()
            RowText("Белки", " ${product?.proteinsPer100Grams} ${product?.measureUnit} ")
            DividerText()
            RowText("Жиры", " ${product?.fatsPer100Grams} ${product?.measureUnit} ")
            DividerText()
            RowText(
                "Углеводы",
                " ${product?.carbohydratesPer100Grams} ${product?.measureUnit} "
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    if (product != null) {
                        onProductClick(product)
                    }
                }
            ) {
                Text(
                    //stringResource(id = R.string.dots),
                    text = "В корзину за ${product?.priceCurrent?.div(100)} Р",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 16.sp,
                )
            }
            // Spacer(modifier = Modifier.height(100.dp))
        }
    }


}

@Composable
fun DividerText() {
    HorizontalDivider(
        modifier = Modifier.padding(top = 1.dp, bottom = 1.dp),
        thickness = 1.dp,
        color = Color.Black
    )
}

@Composable
fun RowText(first: String, second: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = first,
            lineHeight = 20.sp,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            //  modifier = Modifier.wrapContentWidth(align = Alignment.Start)
        )
        Text(
            text = second,
            lineHeight = 20.sp,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            //  modifier = Modifier.wrapContentWidth(align = Alignment.End)
        )
    }
}