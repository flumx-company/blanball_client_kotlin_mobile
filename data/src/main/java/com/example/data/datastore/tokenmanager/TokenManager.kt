package com.example.data.datastore.tokenmanager

import kotlinx.coroutines.flow.Flow

interface TokenManager {

  fun getAccessToken(): Flow<String?>

  suspend fun saveAccessToken(token: String)

  suspend fun deleteAccessToken()

  fun getRefreshToken(): Flow<String?>

  suspend fun saveRefreshToken(token: String)

  suspend fun deleteRefreshToken()
}