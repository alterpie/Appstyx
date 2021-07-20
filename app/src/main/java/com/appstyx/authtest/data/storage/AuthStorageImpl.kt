package com.appstyx.authtest.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthStorageImpl @Inject constructor(private val context: Context) : AuthStorage {

    companion object {
        private val KEY_TOKEN = stringPreferencesKey("KEY_TOKEN")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")
    private val dataStore get() = context.dataStore

    override suspend fun saveToken(token: String) {
        dataStore.edit { it[KEY_TOKEN] = token }
    }

    override suspend fun getToken(): String? {
        return dataStore.data.map { it[KEY_TOKEN] }.firstOrNull()
    }

    override suspend fun clearToken() {
        dataStore.edit { it.clear() }
    }
}
