package com.pedidosya.kata.fruit_store.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun CartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold {
            Text(modifier = Modifier.padding(it), text = "should show items added to cart")
        }
    }
}