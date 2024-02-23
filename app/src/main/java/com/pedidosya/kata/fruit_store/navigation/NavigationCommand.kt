package com.pedidosya.kata.fruit_store.navigation

import androidx.navigation.NamedNavArgument

internal interface NavigationCommand {
    val arguments: List<NamedNavArgument>
    val destination: String
}
