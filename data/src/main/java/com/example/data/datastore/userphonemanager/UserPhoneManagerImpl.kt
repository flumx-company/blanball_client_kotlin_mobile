package com.example.data.datastore.userphonemanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val USER_PHONE_NUMBER = stringPreferencesKey("user_phone_number")

class UserPhoneManagerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    UserPhoneManager {

    override fun getUserPhone(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_PHONE_NUMBER]
        }
    }

    override suspend fun safeUserPhone(phone: String) {
        dataStore.edit { preferences ->
            preferences[USER_PHONE_NUMBER] = phone
        }
    }

    override suspend fun deleteUserPhone() {
        dataStore.edit { preferences ->
            preferences.remove(USER_PHONE_NUMBER)
        }
    }
}