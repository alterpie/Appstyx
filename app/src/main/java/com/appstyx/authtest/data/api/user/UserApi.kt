package com.appstyx.authtest.data.api.user

import com.appstyx.authtest.data.api.user.model.UserResponse

interface UserApi {
    suspend fun getUser():UserResponse
}
