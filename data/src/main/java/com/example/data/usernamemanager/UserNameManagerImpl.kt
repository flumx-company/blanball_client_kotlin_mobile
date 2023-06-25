package com.example.data.usernamemanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val USER_NAME = stringPreferencesKey("user_name")

class UserNameManagerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    UserNameManager {

    override fun getUserName(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_NAME]
        }
    }

    override suspend fun safeUserPhone(userName: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = userName
        }
    }

    override suspend fun deleteUserPhone() {
        dataStore.edit { preferences ->
            preferences.remove(USER_NAME)
        }
    }
}