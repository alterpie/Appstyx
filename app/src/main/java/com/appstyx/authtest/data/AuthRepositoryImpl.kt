package com.appstyx.authtest.data

import com.appstyx.authtest.data.api.auth.AuthApi
import com.appstyx.authtest.data.storage.AuthStorage
import com.appstyx.authtest.domain.auth.AuthRepository
import com.appstyx.authtest.domain.auth.Gender
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val authStorage: AuthStorage,
) : AuthRepository {

    override suspend fun getGenders(): List<Gender> {
        return authApi.getGenders().data.genders.map { Gender(it.id, it.name) }
    }

    override suspend fun signup(email: String, firstName: String, lastName: String, genderId: String): String {
        return authApi.signup(email, firstName, lastName, genderId).data.token
            .also { authStorage.saveToken(it) }
    }

    override suspend fun isAuthorised(): Boolean {
        return authStorage.getToken() != null
    }
}
