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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun VerticalHillButton(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
        modifier
            .intoVertical()
            .sizeIn(116.dp, 56.dp)
            .clip(HillShape)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.primary)
            .padding(bottom = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            modifier =
            Modifier
                .size(16.dp)
                .offset(y = 5.dp),
            tint = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

private fun Modifier.intoVertical() =
    layout { measurable, constraints ->
        val placeable =
            measurable.measure(
                Constraints(
                    minHeight = constraints.minWidth,
                    maxHeight = constraints.maxWidth,
                    minWidth = constraints.minHeight,
                    maxWidth = constraints.maxHeight,
                ),
            )
        layout(placeable.height, placeable.width) {
            placeable.placeWithLayer(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2),
                layerBlock = { rotationZ = -90f },
            )
        }
    }

private val HillShape =
    object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density,
        ): Outline {
            val (width, height) = size

            return Outline.Generic(
                Path().apply {
                    moveTo(
                        x = width * 0f,
                        y = height * 1f,
                    )
                    lineTo(
                        x = width * 0.125f,
                        y = height * 0.425f,
                    )
                    quadraticTo(
                        x1 = width * 0.2f,
                        y1 = height * 0f,
                        x2 = width * 0.4f,
                        y2 = height * 0f,
                    )
                    lineTo(
                        x = width - (width * 0.4f),
                        y = height * 0f,
                    )
                    quadraticTo(
                        x1 = width - (width * 0.2f),
                        y1 = height * 0f,
                        x2 = width - (width * 0.125f),
                        y2 = height * 0.425f,
                    )
                    lineTo(
                        x = width * 1f,
                        y = height * 1f,
                    )
                },
            )
        }
    }
