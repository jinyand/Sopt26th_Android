package com.example.a3rdseminar_practice.network

import com.example.a3rdseminar_practice.data.RequestLogin
import com.example.a3rdseminar_practice.data.RequestRegister
import com.example.a3rdseminar_practice.data.ResponseLogin
import com.example.a3rdseminar_practice.data.ResponseRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestInterface{
    @POST("/user/signin")
    fun requestLogin(@Body body : RequestLogin) : Call<ResponseLogin>

    @POST("/user/signup")
    fun requestRegister(@Body body : RequestRegister) : Call<ResponseRegister>
}