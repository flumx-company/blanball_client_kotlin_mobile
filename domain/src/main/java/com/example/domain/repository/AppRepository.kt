package com.example.domain.repository

import com.example.domain.entity.LoginResultEntity
import com.example.domain.entity.RequestResetResultEntity

interface AppRepository {
    suspend fun login(email: String, password: String): LoginResultEntity
    suspend fun requestReset(email: String): RequestResetResultEntity
}