package com.appstyx.authtest.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    @SerialName("name") val name: String,
    @SerialName("message") val message: String,
    @SerialName("statusCode") val statusCode: Int,
    @SerialName("errorCode") val errorCode: Int,
    @SerialName("errors") val errors: List<ApiErrorDto>? = null
)
