package com.galia.dev.pizza.cart.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.galia.dev.pizza.R
import com.galia.dev.pizza.cart.CartViewModel
import com.galia.dev.pizza.cart.getStubOrderedPizza
import com.google.android.material.composethemeadapter.MdcTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = { AppBar(onBackClick = onBackClick) }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            val listOrder = cartViewModel.orderedPizza
            item {
                TextTitle()
            }
            items(listOrder) { pizza ->
                OrderedPizza(pizza = pizza)
            }
            item {
                OrderInfo(listOrder.fold(0) { r, t -> r + t.pizza.price })
            }
            item {
                BuyButton()
            }
        }
    }
}

@Composable
private fun TextTitle(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.PaddingSmall)
    ) {
        Text(
            text = stringResource(id = R.string.title_cart),
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
private fun AppBar(onBackClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 12.dp),
                )
            }

        },
        title = {
            androidx.compose.material.Text(text = stringResource(id = R.string.menu_cart))
        }
    )
}

@Composable
fun BuyButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal =  Dimens.PaddingSmall, vertical = Dimens.PaddingNormal)
    ) {
        Text(
            text = stringResource(id = R.string.agree_cart),
            fontSize = 20.sp,
            color = MaterialTheme.colors.surface
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CartPreview() {
    MdcTheme {
        Scaffold(
            topBar = { AppBar({}) }
        ) { innerPadding ->

            LazyColumn(contentPadding = innerPadding) {
                item {
                    TextTitle()
                }
                items(getStubOrderedPizza().toMutableStateList()) { pizza ->
                    OrderedPizza(pizza = pizza)
                }
                item {
                    OrderInfo(2000)
                }
                item {
                    BuyButton()
                }
            }
        }
    }
}
