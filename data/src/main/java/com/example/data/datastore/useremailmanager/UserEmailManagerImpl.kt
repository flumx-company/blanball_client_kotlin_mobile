package com.example.data.datastore.useremailmanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val USER_EMAIL = stringPreferencesKey("user_email")

class UserEmailManagerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : UserEmailManager  {
    override fun getUserEmail(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_EMAIL]
        }
    }

    override suspend fun safeUserEmail(userEmail: String) {
        dataStore.edit { preferences ->
            preferences[USER_EMAIL] = userEmail
        }
    }

    override suspend fun deleteUserEmail() {
        dataStore.edit {
            preferences -> preferences.remove(USER_EMAIL)
        }
    }
}