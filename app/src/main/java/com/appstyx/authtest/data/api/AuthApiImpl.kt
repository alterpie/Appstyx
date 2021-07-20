package com.appstyx.authtest.data.api

import com.appstyx.authtest.data.model.GendersResponse
import com.appstyx.authtest.data.model.TokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import javax.inject.Inject

class AuthApiImpl @Inject constructor(
    private val httpClient: HttpClient
): AuthApi {

    override suspend fun getGenders(): GendersResponse {
        return httpClient.get("genders")
    }

    override suspend fun signup(email: String, firstName: String, lastName: String, genderId: String): TokenResponse {
        return httpClient.post("users"){
            parameter("email", email)
            parameter("firstName", firstName)
            parameter("lastName", lastName)
            parameter("gender", genderId)
        }
    }
}