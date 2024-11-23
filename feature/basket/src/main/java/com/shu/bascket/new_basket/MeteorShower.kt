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

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import com.shu.design_system.theme.JetFoodiesTheme
import kotlin.random.Random
import androidx.compose.ui.unit.IntOffset as PixelOffset
import androidx.compose.ui.unit.IntSize as PixelSize

typealias PixelPerSec = Int
typealias Pixel = Int

@Composable
internal fun MeteorShower(
    meteorCount: Int,
    modifier: Modifier = Modifier,
    speed: PixelPerSec = 800,
    maxDelayInMillis: Int = 2000,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        var contentSize by remember { mutableStateOf(PixelSize(0, 0)) }
        val meteors = remember { mutableStateListOf<MeteorState>() }

        LaunchedEffect(contentSize, meteorCount) {
            val contentWidthHalf = contentSize.width / 2

            meteors.clear()
            repeat(meteorCount) {
                meteors +=
                    MeteorState(
                        startX =
                        Random.nextInt(
                            -contentWidthHalf,
                            constraints.maxWidth - contentWidthHalf,
                        ),
                        delayInMillis = Random.nextInt(0, maxDelayInMillis),
                    )
            }
        }

        val startLine: Pixel = -contentSize.height
        val endLine: Pixel = constraints.maxHeight

        meteors.forEach { meteor ->
            val animatable = remember(startLine) { Animatable(initialValue = startLine.toFloat()) }
            LaunchedEffect(meteor) {
                animatable.animateTo(
                    targetValue = endLine.toFloat(),
                    animationSpec =
                    tween(
                        durationMillis = (endLine / speed) * 1000,
                        delayMillis = meteor.delayInMillis,
                        easing = LinearEasing,
                    ),
                )
            }

            Box(
                modifier =
                Modifier
                    .onSizeChanged {
                        contentSize = it
                    }
                    .offset { PixelOffset(x = meteor.startX, y = animatable.value.toInt()) },
            ) {
                content()
            }
        }
    }
}

internal data class MeteorState(
    val startX: Int,
    val delayInMillis: Int,
)

@Preview
@Composable
private fun MeteorShowerPreview() {
    JetFoodiesTheme {
        MeteorShower(40) {
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
        }
    }
}
