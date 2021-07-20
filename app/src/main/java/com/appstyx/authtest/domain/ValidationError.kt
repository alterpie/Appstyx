package com.appstyx.authtest.domain

data class ValidationError(
    val content: List<Pair<String, String>>,
) : Throwable()
