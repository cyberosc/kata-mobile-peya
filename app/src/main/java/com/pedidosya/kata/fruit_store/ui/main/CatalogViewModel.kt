package com.pedidosya.kata.fruit_store.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedidosya.kata.fruit_store.model.Item
import com.pedidosya.kata.fruit_store.network.ItemService
import com.pedidosya.kata.fruit_store.ui.components.directions.FruitStoreNavigation
import com.pedidosya.kata.fruit_store.ui.components.directions.FruitStoreNavigationFlows
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
internal class CatalogViewModel @Inject constructor (
    private val fruitStoreNavigationFlows: FruitStoreNavigationFlows
) : ViewModel() {

    private val _fruits = MutableLiveData<List<Item>>()
    val fruits: LiveData<List<Item>>
        get() = _fruits

    private val _spinner = MutableLiveData(false)

    private lateinit var service : ItemService

    init {
        initService()
        getFruits()
    }

    private fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-e65dee-fruitstore1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ItemService::class.java)
    }

    private fun getFruits() {
        _fruits.value = emptyList()
        viewModelScope.launch {
            _spinner.value = true
            try {
                val result = withContext(Dispatchers.IO) {
                    service.list()
                }
               _fruits.value = result.body()
            }
            catch (e: Throwable) {
                Log.e("CatalogViewModel","Exception happened $e")
            } finally {
                _spinner.value = false
            }

        }
    }

    fun addFruit(item: Item) {
        // TODO: Add item to cart
    }

    fun navigateToDetail() {
        viewModelScope.launch {
            fruitStoreNavigationFlows.navigate(FruitStoreNavigation.cart)
        }
    }
}