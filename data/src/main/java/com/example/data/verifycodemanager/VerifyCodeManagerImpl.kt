package com.example.data.verifycodemanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val VERIFY_CODE = stringPreferencesKey("verify_code")

class VerifyCodeManagerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) : VerifyCodeManager {

    override fun getVerifyCode(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[VERIFY_CODE]
        }
    }

    override suspend fun saveVerifyCode(code: String) {
        dataStore.edit { preferences ->
            preferences[VERIFY_CODE] = code
        }
    }

    override suspend fun deleteVerifyCode() {
       dataStore.edit { preferences -> preferences.remove(VERIFY_CODE) }
    }
}