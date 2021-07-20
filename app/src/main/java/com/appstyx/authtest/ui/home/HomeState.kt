package com.appstyx.authtest.ui.home

data class HomeState(
    val firstName: String,
    val lastName: String,
    val avatarUrl: String?
) {
    companion object {
        val Initial = HomeState("", "", null)
    }
}
