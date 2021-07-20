package com.appstyx.authtest.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class AuthApiImpl @Inject constructor(
    private val httpClient: HttpClient
): AuthApi {

    override suspend fun getGenders(): GendersResponse {
        return httpClient.get("genders")
    }
}
