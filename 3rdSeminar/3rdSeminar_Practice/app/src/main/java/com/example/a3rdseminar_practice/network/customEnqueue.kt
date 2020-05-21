package com.example.a3rdseminar_practice.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun<ResponseType> Call<ResponseType>.customEnqueue(
    onFail : () -> Unit = { Log.d("network", "통신에 실패했습니다.")},
    onSuccess : (ResponseType) -> Unit,
    onError : () -> Unit
) {
    this.enqueue(object : Callback<ResponseType> {

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            onFail()
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            response.body()?.let{
                // response.body()가 있다면 statusCode가 200-300 사이
                onSuccess(it) // 통신결과를 전달
            } ?: onError() // null이라면 onError
        }

    })
}