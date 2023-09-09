package com.haneum.petconnect.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSetting {
    private const val BASE_URL = "http://192.168.0.20:8000/"

    private val client: Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance(): Retrofit{
        return client
    }
}