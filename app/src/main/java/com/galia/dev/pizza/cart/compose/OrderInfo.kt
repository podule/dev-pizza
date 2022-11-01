package com.galia.dev.pizza.cart.compose

import androidx.compose.foundation.layout.*
import com.galia.dev.pizza.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
fun OrderInfo(sum: Int, modifier: Modifier = Modifier) {
    val localModifier = modifier.fillMaxWidth()
    Column(modifier = localModifier.padding(vertical = Dimens.PaddingNormal)) {
        OrderInfoItem(
            stringResource(id = R.string.delivery),
            stringResource(id = R.string.free),
            localModifier
        )
        OrderInfoItem(
            stringResource(id = R.string.sum_order),
            stringResource(id = R.string.price_cart, formatArgs = arrayOf(sum)),
            localModifier
        )
    }
}

@Composable
fun OrderInfoItem(text: String, value: String, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(all = Dimens.PaddingSmall)
    ) {
        val fontSize = 20.sp
        Text(text = text, fontSize = fontSize)
        Text(text = value, fontSize = fontSize)

    }
}

@Preview
@Composable
fun OrderInfoPreview() {
    MdcTheme {
        OrderInfo(2000)
    }
}