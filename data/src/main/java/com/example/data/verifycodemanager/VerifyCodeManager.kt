package com.example.data.verifycodemanager

import kotlinx.coroutines.flow.Flow

interface VerifyCodeManager {

    fun getVerifyCode(): Flow<String?>

    suspend fun saveVerifyCode(code: String)

    suspend fun deleteVerifyCode()
}