package com.pedidosya.kata.fruit_store.ui.components.directions

import com.pedidosya.kata.fruit_store.navigation.NavigationCommand
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FruitStoreNavigationFlows @Inject constructor() {

    private val _navigate = MutableSharedFlow<NavigationCommand>()
    val navigate: SharedFlow<NavigationCommand>
        get() = _navigate

    suspend fun navigate(destination: NavigationCommand) {
        _navigate.emit(destination)
    }
}