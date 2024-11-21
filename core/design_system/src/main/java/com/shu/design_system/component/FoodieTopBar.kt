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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shu.design_system.theme.JetFoodiesTheme

@Composable
fun FoodieTopBar(
    title: @Composable RowScope.() -> Unit,
    action: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    navigation: @Composable RowScope.() -> Unit = { },
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        modifier
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top))
            .padding(top = 28.dp)
            .heightIn(min = 116.dp)
            .wrapContentSize()
            .fillMaxWidth(),
    ) {
        navigation()
        title()
        action()
    }
}

@Preview
@Composable
private fun KristinaCookieTopBarSingleLineTitlePreview() {
    JetFoodiesTheme {

        FoodieTopBar(
            title = {
                Text(
                    "Food",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineMedium,
                )
            },
            action = {
                IconButton(onClick = { }) {
                    Icon(Icons.Rounded.MoreVert, contentDescription = null)
                }
            },
            navigation = {
                IconButton(onClick = { }, modifier = Modifier.align(Alignment.Top)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            },
            modifier = Modifier.background(color = Color.White),
        )
    }
}

@Preview
@Composable
private fun KristinaCookieTopBarTwoLineTitlePreview() {
    JetFoodiesTheme {
        FoodieTopBar(
            title = {
                Text(
                    "Food\nModule",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineMedium,
                )
            },
            action = {
                IconButton(onClick = { }) {
                    Icon(Icons.Rounded.MoreVert, contentDescription = null)
                }
            },
            navigation = {
                IconButton(onClick = { }, modifier = Modifier.align(Alignment.Top)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            },
            modifier = Modifier.background(color = Color.White),
        )
    }
}

@Preview
@Composable
private fun KristinaCookieTopBarThreeLinePreview() {
    JetFoodiesTheme {
        FoodieTopBar(
            title = {
                Text(
                    "Food\nModule\nTopBar",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineMedium,
                )
            },
            action = {
                IconButton(onClick = { }) {
                    Icon(Icons.Rounded.MoreVert, contentDescription = null)
                }
            },
            navigation = {
                IconButton(onClick = { }, modifier = Modifier.align(Alignment.Top)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            },
            modifier = Modifier.background(color = Color.White),
        )
    }
}
