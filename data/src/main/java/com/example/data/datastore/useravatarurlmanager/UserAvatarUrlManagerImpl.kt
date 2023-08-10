package com.example.data.datastore.useravatarurlmanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val USER_AVATAR_URL = stringPreferencesKey("user_avatar_url")

class UserAvatarUrlManagerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : UserAvatarUrlManager
{
    override fun getAvatarUrl(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_AVATAR_URL]
        }
    }

    override suspend fun safeAvatarUrl(avatarUrl: String) {
        dataStore.edit { preferences ->
            preferences[USER_AVATAR_URL] = avatarUrl
        }
    }

    override suspend fun deleteAvatarUrl() {
       dataStore.edit { preferences ->
           preferences.remove(USER_AVATAR_URL)
       }
    }
}