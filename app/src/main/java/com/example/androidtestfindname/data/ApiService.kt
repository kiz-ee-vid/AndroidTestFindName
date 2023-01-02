package com.example.androidtestfindname.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("https://api.agify.io/")
    suspend fun getAge(@Query("name") name: String): Response<ApiItem>
}