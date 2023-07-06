package com.example.data.datastore.remembermemanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val REMEMBER_ME_FLAG = booleanPreferencesKey("remember_me_boolean_flag")

class RememberMeMangerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : RememberMeManager {

    override fun getRememberMeFlag(): Flow<Boolean?> {
        return dataStore.data.map { preferences ->
            preferences[REMEMBER_ME_FLAG]
        }
    }

    override suspend fun saveRememberMeFlag(flag: Boolean) {
        dataStore.edit { preferences ->
            preferences[REMEMBER_ME_FLAG] = flag
        }
    }

    override suspend fun deleteRememberMeFlag() {
        dataStore.edit { preferences ->
            preferences.remove(REMEMBER_ME_FLAG)
        }
    }
}