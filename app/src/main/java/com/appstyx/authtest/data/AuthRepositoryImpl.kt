package com.appstyx.authtest.data

import com.appstyx.authtest.domain.AuthRepository
import com.appstyx.authtest.domain.Gender
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun getGenders(): List<Gender> {
        return authApi.getGenders().data.genders.map { Gender(it.id, it.name) }
    }
}
