package com.pedidosya.kata.fruit_store

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pedidosya.kata.fruit_store.ui.detail.CartScreen
import com.pedidosya.kata.fruit_store.ui.components.directions.FruitStoreNavigation
import com.pedidosya.kata.fruit_store.ui.components.directions.FruitStoreNavigationFlows
import com.pedidosya.kata.fruit_store.ui.main.CatalogScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var navigationFlows: FruitStoreNavigationFlows

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val startNavigation = FruitStoreNavigation.base.destination

                NavigationListener(navController)

                NavHost(navController = navController, startDestination = startNavigation) {
                    setNavigationScheme()
                }
            }
        }
    }

    private fun NavGraphBuilder.setNavigationScheme() {
        composable(FruitStoreNavigation.base.destination) {
            CatalogScreen(hiltViewModel())
        }
        composable(FruitStoreNavigation.cart.destination) {
            CartScreen()
        }
    }

    @Composable
    private fun NavigationListener(navController: NavHostController) {
        LaunchedEffect(key1 = navigationFlows.navigate) {
            navigationFlows.navigate.collect {
                navController.navigate(it.destination)
            }
        }
    }
}