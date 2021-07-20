package com.appstyx.authtest.domain.auth

interface AuthRepository {
    suspend fun getGenders(): List<Gender>
    suspend fun signup(email: String, firstName: String, lastName: String, genderId: String): String
    suspend fun isAuthorised():Boolean
}
