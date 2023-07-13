package com.example.myapplication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val apiUrl = "http://restapi.adequateshop.com/api/"
    private val imageUrl = "https://jsonplaceholder.typicode.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getImage(): Retrofit {
        return Retrofit.Builder().baseUrl(imageUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}