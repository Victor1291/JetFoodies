package com.shu.bascket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shu.modules.BasketEntity

@Composable
fun ProductCard(
    product: BasketEntity
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        ) {

            Image(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.burger),
                contentDescription = "image"
            )
            Column {
                Text(
                    text = product.name ?: "no data",
                    color = MaterialTheme.colorScheme.primary
                )

                Row {

                    Image(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.minus),
                        contentDescription = "image"
                    )

                    Text(
                        text = product.count.toString(),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }

            Text(
                text = " ${product.priceCurrent.toString()} Р",
                color = MaterialTheme.colorScheme.primary
            )


        }
    }
}