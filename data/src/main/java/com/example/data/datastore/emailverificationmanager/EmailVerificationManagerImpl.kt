package com.example.data.datastore.emailverificationmanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val IS_EMAIL_VERIFICATION_STATE = booleanPreferencesKey("is_email_verification_state")

class EmailVerificationManagerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    EmailVerificationManager {

    override fun getIsEmailVerified(): Flow<Boolean?> {
        return dataStore.data.map { preferences ->
            preferences[IS_EMAIL_VERIFICATION_STATE]
        }
    }

    override suspend fun saveIsEmailVerifiedState(isEmailVerified: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_EMAIL_VERIFICATION_STATE] = isEmailVerified
        }
    }

    override suspend fun deleteIsEmailVerifiedState() {
        dataStore.edit { preferences ->
            preferences.remove(IS_EMAIL_VERIFICATION_STATE)
        }
    }
}