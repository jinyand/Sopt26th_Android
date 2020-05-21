package com.example.a3rdseminar_practice.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RequestBookToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: RequestBookInterface = retrofit.create(RequestBookInterface::class.java)
}