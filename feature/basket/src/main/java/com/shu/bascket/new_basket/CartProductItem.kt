/*
 * Copyright 2024 Erfan Sn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shu.bascket.new_basket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shu.bascket.R
import com.shu.design_system.component.ProductBackground
import com.shu.design_system.component.ProductImage
import com.shu.design_system.modifier.LocalNavAnimatedContentScope
import com.shu.modules.CartProduct
import com.shu.modules.Product

@Composable
internal fun CartProductItem(
  onClick: () -> Unit,
  cartProduct: CartProduct,
  modifier: Modifier = Modifier,
) {
    ListItem(
        leadingContent = {
            CompositionLocalProvider(LocalNavAnimatedContentScope provides null) {
                ProductImage(
                    background =
                    ProductBackground(
                        color = cartProduct.backgroundColor,
                        cornerSize = 12.dp,
                    ),
                    image = painterResource(id = cartProduct.imageId),
                    modifier = Modifier.padding(8.dp),
                )
            }
        },
        headlineContent = {
            Text(
                text = cartProduct.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        },
        supportingContent = {
            Text(
                text = "Qty: ${cartProduct.quantity}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp),
                fontWeight = FontWeight.SemiBold,
            )
        },
        trailingContent = {
            Text(
                text = cartProduct.totalPrice.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(end = 6.dp),
            )
        },
        modifier =
        modifier
          .clickable { onClick() }
          .height(128.dp)
          .fillMaxWidth(),
        colors =
        ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background,
            headlineColor = MaterialTheme.colorScheme.onBackground,
            trailingIconColor = MaterialTheme.colorScheme.onBackground,
            supportingColor = Color(0xFF5A5350),
        ),
    )
}


fun Product.toCartProduct(quantity: Int) =
    CartProduct(
        id = id ?: 1,
       // backgroundColor = Color.Cyan,
        imageId = R.drawable.pizza,
        title = name ?: "no name",
        quantity = quantity,
        price = priceCurrent ?: 100,
    )

/*
internal val sampleCartProducts =
  sampleVitrineItems.map {
    CartProduct(
      id = it.id,
      backgroundColor = it.backgroundColor,
      imageId = it.imageId,
      title = it.title,
      quantity = Random.nextInt(1, 10),
      price = it.priceInCent,
    )
  }

@Preview
@Composable
private fun CartProductItemPreview() {
  KristinaCookieTheme {
    CartProductItem(
      cartProduct = sampleCartProducts.first(),
      onClick = { },
    )
  }
}
*/
