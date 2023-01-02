package com.example.androidtestfindname.data

import com.example.androidtestfindname.domain.IRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : IRepository {

    override suspend fun getAge(name: String): ApiItem? {
        println("HELLO")
        return apiService.getAge(name).body()
    }
}