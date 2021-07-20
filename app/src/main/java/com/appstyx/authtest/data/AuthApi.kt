package com.appstyx.authtest.data

interface AuthApi {
    suspend fun getGenders(): GendersResponse
}
