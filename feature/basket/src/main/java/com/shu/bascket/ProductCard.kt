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
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.burger),
                contentDescription = "image"
            )
            Column(
                modifier = Modifier,
            ) {
                Text(
                    text = product.name ?: "no data",
                    maxLines = 2 ,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.minus),
                        modifier = Modifier.padding(top = 10.dp, end = 20.dp),
                        contentDescription = "image"
                    )

                    Text(
                        text = product.count.toString(),
                        color = MaterialTheme.colorScheme.primary
                    )

                    Icon(
                        imageVector = Icons.Default.Add,
                        modifier = Modifier.padding(start = 20.dp),
                        contentDescription = null
                    )
                }
            }

            Text(
                text = " ${product.priceCurrent?.div(100)} Р",
                color = MaterialTheme.colorScheme.primary
            )


        }
    }
}