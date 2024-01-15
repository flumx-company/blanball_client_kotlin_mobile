package com.example.data.datastore.emailverificationmanager

import kotlinx.coroutines.flow.Flow

interface EmailVerificationManager {
     fun getIsEmailVerified(): Flow<Boolean?>

     suspend fun saveIsEmailVerifiedState(isEmailVerified: Boolean)

     suspend fun deleteIsEmailVerifiedState()
}