package com.example.data.datastore.useravatarurlmanager

import kotlinx.coroutines.flow.Flow

interface UserAvatarUrlManager {
    fun getAvatarUrl(): Flow<String?>

    suspend fun safeAvatarUrl(avatarUrl: String)

    suspend fun deleteAvatarUrl()
}