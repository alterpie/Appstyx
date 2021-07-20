package com.appstyx.authtest.data

import com.appstyx.authtest.data.api.user.UserApi
import com.appstyx.authtest.domain.user.User
import com.appstyx.authtest.domain.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun getUser(): User {
        return with(userApi.getUser().data.user) { User(id, firstName, lastName, avatarUrl) }
    }
}
