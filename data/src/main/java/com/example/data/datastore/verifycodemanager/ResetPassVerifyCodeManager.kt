package com.example.data.datastore.verifycodemanager

import kotlinx.coroutines.flow.Flow

interface ResetPassVerifyCodeManager {

    fun getResetPassVerifyCode(): Flow<String?>

    suspend fun saveResetPassVerifyCode(code: String)

    suspend fun deleteResetPassVerifyCode()
}