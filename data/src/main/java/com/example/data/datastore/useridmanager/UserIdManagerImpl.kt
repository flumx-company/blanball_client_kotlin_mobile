package com.example.data.datastore.useridmanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val USER_ID  = intPreferencesKey("user_id")

class UserIdManagerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    UserIdManager {

    override fun getUserId(): Flow<Int?> {
        return dataStore.data.map { preferences ->
            preferences[USER_ID]
        }
    }

    override suspend fun saveUserId(id: Int) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = id
        }
    }

    override suspend fun deleteUserId() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ID)
        }
    }
}