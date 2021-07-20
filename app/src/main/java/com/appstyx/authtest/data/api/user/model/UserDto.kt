package com.appstyx.authtest.data.api.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("_id") val id: String,
    @SerialName("avatarUrl") val avatarUrl: String? = null,
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String
)
