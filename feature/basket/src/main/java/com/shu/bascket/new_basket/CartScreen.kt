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
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastRoundToInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.currentStateAsState
import androidx.lifecycle.compose.dropUnlessResumed
import com.shu.bascket.BasketViewModel
import com.shu.design_system.component.FoodieFloatingScaffold
import com.shu.design_system.component.FoodieTopBar
import com.shu.design_system.component.VerticalHillButton
import com.shu.design_system.modifier.overlappedBackgroundColor
import com.shu.design_system.modifier.scaleEffectValue
import com.shu.design_system.modifier.sharedElementAnimSpec
import com.shu.design_system.theme.cornerSize
import com.shu.modules.CartProduct
import kotlinx.coroutines.launch

@Composable
fun CartRoute(
    viewModel: BasketViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit ,
    onNavigateToProduct: (id: Int) -> Unit ,
    modifier: Modifier = Modifier,
) {

    val basket by viewModel.basket.collectAsStateWithLifecycle()
    CartScreen(
        onNavigateToHome = onNavigateToHome,
        onNavigateToProduct = onNavigateToProduct,
        modifier = modifier,
        cartProducts = basket,
        totalPrice = 100,
    )
}

@Composable
private fun CartScreen(
    cartProducts: List<CartProduct>,
    onNavigateToHome: () -> Unit,
    onNavigateToProduct: (id: Int) -> Unit,
    totalPrice: Int,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()

    var paymentIsSuccessful by remember { mutableStateOf(false) }
    Box(
        contentAlignment = Alignment.Center,
    ) {
        FoodieFloatingScaffold(
            topBar = {
                val isOverlapped by remember {
                    derivedStateOf {
                        lazyListState.firstVisibleItemIndex > 0 ||
                                lazyListState.firstVisibleItemScrollOffset > 46
                    }
                }
                CartTopBar(
                    onNavigateToHome = onNavigateToHome,
                    modifier = Modifier.overlappedBackgroundColor(isOverlapped),
                )
            },
            floatingBottomBar = {
                if (cartProducts.isNotEmpty()) {
                    var bottomBarHeight by remember { mutableIntStateOf(0) }
                    val bottomBarOffsetY =
                        remember(bottomBarHeight) { Animatable(bottomBarHeight.toFloat()) }

                    val scope = rememberCoroutineScope()
                    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
                        scope.launch {
                            bottomBarOffsetY.animateTo(0f, animationSpec = sharedElementAnimSpec())
                        }
                    }
                    CartBottomBar(
                        totalPrice = totalPrice,
                        onPayClick = {
                            paymentIsSuccessful = true
                        },
                        modifier =
                        Modifier
                            .onSizeChanged {
                                bottomBarHeight = it.height
                            }
                            .offset {
                                IntOffset(y = bottomBarOffsetY.value.fastRoundToInt(), x = 0)
                            },
                    )
                }
            },
            modifier =
            modifier
                .fillMaxSize()
                .then(
                    if (paymentIsSuccessful) Modifier.pointerInput(Unit) { } else Modifier,
                ),
        ) {
            var bottomOffsetY by rememberSaveable {
                mutableIntStateOf(0)
            }
            LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
                bottomOffsetY =
                    lazyListState.layoutInfo.viewportSize.height -
                            (
                                    lazyListState.layoutInfo.visibleItemsInfo
                                        .lastOrNull()
                                        ?.let { info -> info.size + info.offset } ?: 0
                                    )
            }

            val scope = rememberCoroutineScope()
            LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
                scope.launch {
                    if (bottomOffsetY > 0) {
                        lazyListState.animateScrollBy(
                            bottomOffsetY.toFloat(),
                            animationSpec = sharedElementAnimSpec(),
                        )
                        bottomOffsetY = 0
                    }
                }
            }

            CartContent(
                cartProducts = cartProducts,
                contentPadding = it,
                state = lazyListState,
                onNavigateToProduct = onNavigateToProduct,
            )
        }
        if (paymentIsSuccessful) {
            PaymentSuccessfulPane(
                modifier =
                Modifier
                    .pointerInput(Unit) { },
            )
        }
    }
}

@Composable
private fun CartTopBar(
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var enteredCompletely by remember { mutableStateOf(false) }
    val transition = updateTransition(enteredCompletely, label = "topbar_transition")
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        enteredCompletely = true
    }

    val catalogOffsetX by transition.animateDp(
        label = "catalog",
        transitionSpec = { sharedElementAnimSpec() },
    ) {
        if (!it) {
            (-56).dp
        } else {
            0.dp
        }
    }
    val numberScale by transition.animateFloat(
        label = "number",
        transitionSpec = { sharedElementAnimSpec() },
    ) {
        if (!it) {
            0f
        } else {
            1f
        }
    }
    FoodieTopBar(
        modifier = modifier,
        navigation = {
            VerticalHillButton(
                onClick = onNavigateToHome,
                title = "Catalog",
                modifier =
                Modifier
                    .offset {
                        IntOffset(x = catalogOffsetX.roundToPx(), y = 0)
                    }
                    .rotate(180f),
            )
        },
        title = {
            Text(
                "Cart",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.SemiBold,
            )
        },
        action = {
            Box(
                contentAlignment = Alignment.Center,
                modifier =
                Modifier
                    .graphicsLayer {
                        scaleX = numberScale
                        scaleY = scaleX
                    }
                    .padding(end = 24.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    "3",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        },
    )
}

@Composable
private fun CartContent(
    cartProducts: List<CartProduct>,
    onNavigateToProduct: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    state: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
        modifier
            .fillMaxWidth(),
        contentPadding = contentPadding,
        state = state,
    ) {
        itemsIndexed(cartProducts) { index, product ->
            val itemOffsetX =
                remember {
                    Animatable(
                        initialValue = 24.dp * index,
                        typeConverter = Dp.VectorConverter,
                    )
                }
            val currentLifecycleState by LocalLifecycleOwner.current.lifecycle.currentStateAsState()
            LaunchedEffect(currentLifecycleState) {
                if (currentLifecycleState == Lifecycle.State.STARTED) {
                    itemOffsetX.animateTo(0.dp, animationSpec = sharedElementAnimSpec())
                } else {
                    itemOffsetX.snapTo(0.dp)
                }
            }

            CartProductItem(
                cartProduct = product,
                onClick = dropUnlessResumed { onNavigateToProduct(product.id) },
                modifier =
                Modifier.offset {
                    IntOffset(x = itemOffsetX.value.roundToPx(), y = 0)
                },
            )
        }
    }
}

@Composable
private fun CartBottomBar(
    totalPrice: Int,
    onPayClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        modifier
            .pointerInput(Unit) {}
            .clip(CurvedShape)
            .background(MaterialTheme.colorScheme.primary)
            .padding(bottom = 8.dp, top = 24.dp)
            .navigationBarsPadding()
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(start = 48.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                "Total amount",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                totalPrice.toString(),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.SemiBold,
            )
        }
        val interactionSource = remember { MutableInteractionSource() }
        val scaleEffectValue by interactionSource.scaleEffectValue()
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier =
            Modifier
                .graphicsLayer {
                    scaleX = scaleEffectValue
                    scaleY = scaleEffectValue
                }
                .padding(end = 8.dp)
                .clip(RoundedCornerShape(MaterialTheme.cornerSize.large))
                .clickable(
                    interactionSource = interactionSource,
                    indication = ripple(),
                ) { onPayClick() }
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 32.dp, vertical = 42.dp),
        ) {
            CompositionLocalProvider(
                LocalContentColor provides MaterialTheme.colorScheme.onBackground,
            ) {
                Text(
                    text = "Pay",
                    style = MaterialTheme.typography.titleLarge,
                    modifier =
                    Modifier
                        .offset(x = 6.dp),
                )
                repeat(3) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = null,
                        tint = LocalContentColor.current.copy(alpha = 1f / (3 - it)),
                        modifier =
                        Modifier
                            .size(24.dp)
                            .offset(x = 6.dp * (3 - 1 - it)),
                    )
                }
            }
        }
    }
}

private val CurvedShape =
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
                        x = width * 0f,
                        y = height * 0.35f,
                    )
                    quadraticTo(
                        x1 = width * 0f,
                        y1 = height * 0.103f,
                        x2 = width * 0.08f,
                        y2 = height * 0.08f,
                    )
                    quadraticTo(
                        x1 = width * 0.5f,
                        y1 = height * -0.07f,
                        x2 = width - (width * 0.08f),
                        y2 = height * 0.08f,
                    )
                    quadraticTo(
                        x1 = width - (width * 0f),
                        y1 = height * 0.103f,
                        x2 = width - (width * 0f),
                        y2 = height * 0.35f,
                    )
                    lineTo(
                        x = width * 1f,
                        y = height * 1f,
                    )
                },
            )
        }
    }

/*
@Preview
@Composable
private fun CartScreenPreview() {
  KristinaCookieTheme {
    CartScreen(
      cartProducts = sampleCartProducts,
      onNavigateToHome = { },
      onNavigateToProduct = { },
      totalPrice = 8900,
    )
  }
}
*/
