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

package com.shu.design_system.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.shu.design_system.theme.JetFoodiesTheme

@Composable
fun FoodieScaffold(
    bottomBar: @Composable () -> Unit,
    topBar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        content = content,
    )
}

@Composable
fun FoodieFloatingScaffold(
    topBar: @Composable () -> Unit,
    floatingBottomBar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = {
            // To prevent applying insets padding to bottom side
            Box(Modifier)
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) { innerPadding ->
        Box(
            modifier =
            Modifier
                .fillMaxHeight()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
        ) {
            var bottomBarHeight by remember { mutableIntStateOf(0) }
            content(PaddingValues(bottom = with(LocalDensity.current) { bottomBarHeight.toDp() }))
            Box(
                modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .onSizeChanged {
                        bottomBarHeight = it.height
                    },
            ) {
                floatingBottomBar()
            }
        }
    }
}

@Preview
@Composable
private fun KristinaCookieScaffoldPreview() {
    JetFoodiesTheme {
        FoodieScaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Text(text = "Top Bar")
            },
            bottomBar = {
                Text(text = "Bottom Bar")
            },
        ) {
            Box(modifier = Modifier.padding(it)) {
                Text(text = "Content")
            }
        }
    }
}

@Preview
@Composable
private fun KristinaCookieFloatingScaffoldPreview() {
    JetFoodiesTheme {
        FoodieFloatingScaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Text(text = "Top Bar")
            },
            floatingBottomBar = {
                Text(text = "Floating bottom Bar")
            },
        ) {
            Box(modifier = Modifier.padding(it)) {
                Text(text = "Content")
            }
        }
    }
}
