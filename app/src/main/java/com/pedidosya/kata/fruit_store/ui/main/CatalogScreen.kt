package com.pedidosya.kata.fruit_store.ui.main

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.asFlow
import com.pedidosya.kata.fruit_store.model.Item
import com.pedidosya.kata.fruit_store.ui.components.directions.FruitStoreNavigationFlows
import kotlin.math.roundToInt

@Composable
internal fun CatalogScreen(
    viewModel: CatalogViewModel
) {
    val fruits by viewModel.fruits.asFlow().collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(bottomBar = {
            GoToCartButton(viewModel)
        }) {
            LazyColumn(Modifier.padding(it)) {

                itemsIndexed(fruits) { index, fruit ->
                    CartItem(fruit, viewModel)
                    Spacer(modifier = Modifier.height(Dp(4F)))
                    if (index < fruits.lastIndex)
                        Divider(color = Color.Black, thickness = 1.dp)
                }
            }
        }
    }
}

@Composable
private fun GoToCartButton(viewModel: CatalogViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            modifier = Modifier.align(Alignment.Center).padding(bottom = 16.dp),
            onClick = { viewModel.navigateToDetail() }) {
            Text(text = "GO TO CART")
        }
    }
}

@Composable
private fun CartItem(
    fruit: Item,
    viewModel: CatalogViewModel
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dp(8f))
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
            ) {

                Text(text = fruit.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Spacer(modifier = Modifier.height(Dp(8F)))

                Text(text = "$ ${fruit.price}")
            }

            Button(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = {
                    viewModel.addFruit(fruit)
                    Toast.makeText(context, "${fruit.name} added to cart", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = "+")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CatalogScreen() {
    CatalogScreen(viewModel = CatalogViewModel(FruitStoreNavigationFlows()))
}
