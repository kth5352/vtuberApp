package com.example.smartapp

import retrofit2.http.GET

interface VtuberApi {
    @GET("vtuber")
    suspend fun getVtubers(): List<Vtuber>
}