package com.example.data.datastore.remembermemanager

import kotlinx.coroutines.flow.Flow

interface RememberMeManager {

    fun getRememberMeFlag(): Flow<Boolean?>

    suspend fun saveRememberMeFlag(flag: Boolean )

    suspend fun deleteRememberMeFlag()
}