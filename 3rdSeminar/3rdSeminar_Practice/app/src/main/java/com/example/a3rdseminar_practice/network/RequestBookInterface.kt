package com.example.a3rdseminar_practice.network

import com.example.a3rdseminar_practice.data.*
import retrofit2.Call
import retrofit2.http.*

interface RequestBookInterface{

    @GET("/v3/search/book")
    fun requestBook(
        @Query("query") title : String,
        @Header("Authorization") key : String
    ): Call<ResponseBook>

}