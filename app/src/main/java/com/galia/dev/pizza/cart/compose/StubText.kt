package com.galia.dev.pizza.cart.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
fun StubText() {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "В разработке",
            modifier = Modifier.padding(24.dp),
            textAlign = TextAlign.Center,
            color = Color.Black.copy(alpha = 0.5f),
            style = MaterialTheme.typography.h5,
        )
    }
}

@Preview
@Composable
fun TestPreview() {
    MdcTheme {
        StubText()
    }
}