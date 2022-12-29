package com.example.androidtestfindname.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET
    suspend fun getAge(@Query("name") name: String): Response<ApiItem>
}