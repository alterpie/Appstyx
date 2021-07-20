package com.appstyx.authtest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    @SerialName("data") val data: Data
) {
    @Serializable
    data class Data(
        @SerialName("token") val token: String,
        @SerialName("refreshToken") val refreshToken: String
    )
}
