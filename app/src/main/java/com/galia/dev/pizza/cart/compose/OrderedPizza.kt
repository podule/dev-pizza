package com.galia.dev.pizza.cart.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.galia.dev.pizza.api.models.OrderedPizza
import com.galia.dev.pizza.api.models.Pizza
import com.galia.dev.pizza.R
import com.google.android.material.composethemeadapter.MdcTheme


@Composable
fun OrderedPizza(pizza: OrderedPizza, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(all = Dimens.PaddingSmall)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {

            PizzaImage(imageUrl = pizza.pizza.url, imageHeight = 70.dp)
            PizzaInfo(pizza = pizza, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun PizzaImage(imageUrl: String, imageHeight: Dp, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data = imageUrl)
            .crossfade(true)
            .error(drawableResId = R.drawable.ic_broken_image)
            .placeholder(drawableResId = R.drawable.shrimps)
            .build()
    )

    Image(
        painter = painter,
        contentScale = ContentScale.FillHeight,
        contentDescription = null,
        modifier = modifier
            .height(imageHeight)
    )
}

@Composable
fun PizzaInfo(pizza: OrderedPizza, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val columnModifier = Modifier
            .padding(horizontal = Dimens.PaddingNormal)
            .padding(top = Dimens.PaddingSmall)
        PizzaInfoItem(
            text = pizza.pizza.title,
            textStyle = MaterialTheme.typography.subtitle1,
            modifier = columnModifier
        )
        PizzaInfoItem(text = pizza.pizza.description, modifier = columnModifier.alpha(0.5f))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
            TextPrice(price = pizza.pizza.price, modifier = columnModifier)
            CountSwitch(count = pizza.count, modifier = columnModifier)
        }
    }
}

@Composable
fun TextPrice(price: Int, modifier: Modifier = Modifier) {
    Surface(
        color = Color.Red.copy(alpha = 0.2f),
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.price_cart, formatArgs = arrayOf(price)),
            modifier = Modifier.padding(horizontal = Dimens.PaddingSmall, vertical = 5.dp)
        )
    }
}

@Composable
fun CountSwitch(count: Int, modifier: Modifier = Modifier) {
    Surface(
        color = Color.Gray.copy(alpha = 0.2f),
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row {
            arrayOf("-", count.toString(), "+").forEach { value ->
                Text(text = value, modifier = Modifier
                    .padding(horizontal = Dimens.PaddingSmall, vertical = 5.dp)
                    .clickable { })
            }
        }

    }
}

@Composable
fun PizzaInfoItem(
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.subtitle2,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = textStyle,
        modifier = modifier
    )
}


@Preview
@Composable
fun OrderedPizzaPreview() {
    MdcTheme {
        OrderedPizza(
            pizza = OrderedPizza(
                1, Pizza(
                    1L,
                    "Пепперони",
                    "Средняя пицца на тонком тесте",
                    "test",
                    "test",
                    140
                ), 1
            )
        )
    }
}