package com.example.data


import com.example.data.backend.ApiService
import com.example.domain.repository.AppRepository

class AppRepositoryImpl(
    private val service: ApiService,
) : AppRepository {

}