package com.example.data.usernamemanager

import kotlinx.coroutines.flow.Flow

interface UserNameManager {
    fun getUserName(): Flow<String?>

    suspend fun safeUserPhone(userName: String)

    suspend fun deleteUserPhone()
}