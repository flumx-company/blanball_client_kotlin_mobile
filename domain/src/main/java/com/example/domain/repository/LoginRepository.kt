package com.example.domain.repository

import com.example.domain.entity.LoginResultEntity

interface LoginRepository {
    suspend fun login(username: String, password: String): LoginResultEntity
}