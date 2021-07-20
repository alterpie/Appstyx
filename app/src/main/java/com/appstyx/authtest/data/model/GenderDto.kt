package com.appstyx.authtest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenderDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
)
