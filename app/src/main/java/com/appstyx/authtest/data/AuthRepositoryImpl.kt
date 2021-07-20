package com.appstyx.authtest.data

import com.appstyx.authtest.data.api.AuthApi
import com.appstyx.authtest.data.storage.AuthStorage
import com.appstyx.authtest.domain.AuthRepository
import com.appstyx.authtest.domain.Gender
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
}
