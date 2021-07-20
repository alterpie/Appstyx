package com.appstyx.authtest.data.api.user

import com.appstyx.authtest.data.api.user.model.UserResponse
import com.appstyx.authtest.data.storage.AuthStorage
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import javax.inject.Inject

class UserApiImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val authStorage: AuthStorage
) : UserApi {

    override suspend fun getUser(): UserResponse {
        return httpClient.get("users/me") {
            authStorage.getToken()?.let { header("Authorization", "Bearer $it") }
        }
    }

}
