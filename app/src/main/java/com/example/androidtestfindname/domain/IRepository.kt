package com.example.androidtestfindname.domain

import com.example.androidtestfindname.data.ApiItem

interface IRepository {
    suspend fun getAge(name: String): ApiItem?
}