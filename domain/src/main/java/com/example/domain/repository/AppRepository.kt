package com.example.domain.repository

import com.example.domain.entity.LoginResultEntity
import com.example.domain.entity.EmailResetResultEntity

interface AppRepository {
    suspend fun login(email: String, password: String): LoginResultEntity
    suspend fun sendEmailPassReset(email: String): EmailResetResultEntity
}