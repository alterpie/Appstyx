package com.appstyx.authtest.data.api.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("data") val data: Data
) {
    @Serializable
    data class Data(
        @SerialName("user") val user: UserDto
    )
}
