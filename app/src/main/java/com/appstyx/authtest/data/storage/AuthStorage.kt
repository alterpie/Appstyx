package com.appstyx.authtest.data.storage

interface AuthStorage {
    suspend fun saveToken(token: String)
}
