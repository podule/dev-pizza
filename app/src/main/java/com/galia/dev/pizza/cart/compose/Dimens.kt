package com.galia.dev.pizza.cart.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.galia.dev.pizza.R

object Dimens {
    val PaddingSmall: Dp
        @Composable get() = dimensionResource(R.dimen.margin_small)

    val PaddingNormal: Dp
        @Composable get() = dimensionResource(R.dimen.margin_normal)
}