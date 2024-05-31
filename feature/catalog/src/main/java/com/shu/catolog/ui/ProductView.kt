package com.shu.catolog.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shu.catolog.HomeViewModel
import com.shu.catolog.R
import com.shu.modules.Product
import com.shu.modules.StateScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductView(
    stateScreen: StateScreen,
    viewModel: HomeViewModel,
    onCategoryClick: (Int) -> Unit,
    onProductClick: (Product) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "icon for navigation item"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {    //navHostController.navigateUp()
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {    //navHostController.navigateUp()
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                },

                )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

            val select by viewModel.selectCategory.collectAsState()

            LazyRow(
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier,
            ) {

                items(stateScreen.category.size) { category ->

                    InputChip(
                        onClick = {
                            onCategoryClick(category)
                            viewModel.changeSelect(category)
                        },
                        label = { Text(stateScreen.category[category].name.toString()) },
                        selected = category == select,
                        border = InputChipDefaults.inputChipBorder(false, false)
                    )
                    /*  Button(
                          modifier = Modifier.height(36.dp),
                          onClick = { onProductClick(category) },
                          contentPadding = PaddingValues(0.dp)
                      ) {
                          Text(
                              text = stateScreen.category[category].name.toString(),
                              modifier = Modifier.padding(horizontal = 16.dp)
                          )
                      }*/

                }
            }

            ProductGrid(products = stateScreen.products, onProductClick =  onProductClick)
        }
    }
}

