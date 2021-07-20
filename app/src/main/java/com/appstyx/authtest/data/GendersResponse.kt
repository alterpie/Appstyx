package com.appstyx.authtest.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GendersResponse(
    @SerialName("data") val data: Data,
) {

    @Serializable
    data class Data(@SerialName("genders") val genders: List<GenderDto>)
}
