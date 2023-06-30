package com.example.data.datastore.tokenmanager

import kotlinx.coroutines.flow.Flow

interface TokenManager {

  fun getToken(): Flow<String?>

  suspend fun saveToken(token: String)

  suspend fun deleteToken()
}