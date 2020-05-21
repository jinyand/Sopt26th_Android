package com.example.a3rdseminar_practice.data

data class ResponseBook (
    val documents : List<ResponseBookData>
)

data class ResponseBookData(
    val title : String,
    val contents : String,
    val authors : Array<String>,
    val thumbnail : String
)