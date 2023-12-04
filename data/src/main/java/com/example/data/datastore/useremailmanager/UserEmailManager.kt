package com.example.data.datastore.useremailmanager

import kotlinx.coroutines.flow.Flow


interface UserEmailManager {
    fun getUserEmail(): Flow<String?>

    suspend fun  safeUserEmail(userEmail: String)

    suspend fun deleteUserEmail()
}