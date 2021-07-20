package com.appstyx.authtest.data.api.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorDto(
    @SerialName("value") val value: String,
    @SerialName("msg") val msg: String,
    @SerialName("param") val param: String
)
