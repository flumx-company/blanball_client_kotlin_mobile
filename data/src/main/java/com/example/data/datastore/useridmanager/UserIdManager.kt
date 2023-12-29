package com.example.data.datastore.useridmanager

import kotlinx.coroutines.flow.Flow

interface UserIdManager {
    fun getUserId(): Flow<Int?>

    suspend fun saveUserId(id: Int )

    suspend fun deleteUserId()
}