package com.example.myapplication.api

import com.example.myapplication.model.Image
import com.example.myapplication.model.Tour
import retrofit2.Response
import retrofit2.http.GET

interface TourApi {
    @GET("Tourist?page=1")
    suspend fun getTours(): Response<Tour>

    @GET("photos")
    suspend fun getImages(): Response<MutableList<Image>>
}