package com.example.data.datastore.verifycodemanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val VERIFY_CODE = stringPreferencesKey("verify_code")

class ResetPassVerifyCodeManagerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    ResetPassVerifyCodeManager {

    override fun getResetPassVerifyCode(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[VERIFY_CODE]
        }
    }

    override suspend fun saveResetPassVerifyCode(code: String) {
        dataStore.edit { preferences ->
            preferences[VERIFY_CODE] = code
        }
    }

    override suspend fun deleteResetPassVerifyCode() {
       dataStore.edit { preferences -> preferences.remove(VERIFY_CODE) }
    }
}