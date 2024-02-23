package com.pedidosya.kata.fruit_store.ui.components.directions

import androidx.navigation.NamedNavArgument
import com.pedidosya.kata.fruit_store.navigation.NavigationCommand

internal object FruitStoreNavigation {

    private const val BASE_PATH = "fruit_store"

    val base = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "$BASE_PATH/catalog"
    }

    val cart = object: NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "$BASE_PATH/cart"

    }
}