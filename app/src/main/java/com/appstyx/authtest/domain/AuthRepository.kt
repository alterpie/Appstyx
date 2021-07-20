package com.appstyx.authtest.domain

interface AuthRepository {
    suspend fun getGenders(): List<Gender>
}
