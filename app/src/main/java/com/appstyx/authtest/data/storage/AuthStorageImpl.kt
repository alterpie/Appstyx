package com.appstyx.authtest.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject

class AuthStorageImpl @Inject constructor(private val context: Context) : AuthStorage {

    companion object {
        private val KEY_TOKEN = stringPreferencesKey("KEY_TOKEN")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { it[KEY_TOKEN] = token }
    }
}
