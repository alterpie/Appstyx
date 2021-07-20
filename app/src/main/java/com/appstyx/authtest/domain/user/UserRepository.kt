package com.appstyx.authtest.domain.user

interface UserRepository {
    suspend fun getUser(): User
}
