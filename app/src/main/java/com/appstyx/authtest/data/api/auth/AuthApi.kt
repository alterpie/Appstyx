package com.appstyx.authtest.data.api.auth

import com.appstyx.authtest.data.model.GendersResponse
import com.appstyx.authtest.data.model.TokenResponse

interface AuthApi {
    suspend fun getGenders(): GendersResponse
    suspend fun signup(email: String, firstName: String, lastName: String, genderId: String): TokenResponse
}
