package com.example.domain.repository

import com.example.domain.entity.EmailResetResultEntity
import com.example.domain.entity.LoginResultEntity
import com.example.domain.entity.RegistrationResultEntity
import com.example.domain.entity.ResetCompleteResultEntity
import com.example.domain.entity.SendCodeResultEntity

interface AppRepository {
    suspend fun login(email: String, password: String): LoginResultEntity
    suspend fun sendEmailPassReset(email: String): EmailResetResultEntity
    suspend fun sendCode(code: String): SendCodeResultEntity
    suspend fun changePassword(newPassword: String): ResetCompleteResultEntity
    suspend fun registration(
        email: String,
        phone: String,
        password: String,
        re_password: String,
        name: String,
        lastName: String,
        gender: String
    ) : RegistrationResultEntity
}