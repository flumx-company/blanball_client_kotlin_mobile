package com.example.data.datastore.usernamemanager

import kotlinx.coroutines.flow.Flow

interface UserNameManager {
    fun getUserName(): Flow<String?>

    suspend fun safeUserName(userName: String)

    suspend fun deleteUserName()
}