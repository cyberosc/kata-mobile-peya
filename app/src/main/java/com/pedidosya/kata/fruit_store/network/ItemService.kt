package com.pedidosya.kata.fruit_store.network

import com.pedidosya.kata.fruit_store.model.Item
import retrofit2.Response
import retrofit2.http.GET


interface ItemService {
    @GET("/fruits")
    suspend fun list(): Response<List<Item>>
}