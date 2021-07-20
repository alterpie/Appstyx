package com.appstyx.authtest.domain

interface AuthRepository {
    suspend fun getGenders(): List<Gender>
    suspend fun signup(email: String, firstName: String, lastName: String, genderId: String): String
}
