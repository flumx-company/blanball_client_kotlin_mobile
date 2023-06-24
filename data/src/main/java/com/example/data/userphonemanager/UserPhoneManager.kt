package com.example.data.userphonemanager

import kotlinx.coroutines.flow.Flow

interface UserPhoneManager {
    fun getUserPhone(): Flow<String?>

    suspend fun safeUserPhone(phone: String)

    suspend fun deleteUserPhone()
}